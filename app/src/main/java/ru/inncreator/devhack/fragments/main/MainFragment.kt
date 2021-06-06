package ru.inncreator.devhack.fragments.main

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import ru.inncreator.devhack.R
import ru.inncreator.devhack.databinding.FragmentMainBinding
import ru.inncreator.devhack.fragments.main.MainFragmentDirections.Companion.actionDestMainToDestEvents
import timber.log.Timber


class MainFragment : Fragment() {

    private val vm: MainFragmentViewModel by inject()

    private val adapter by lazy { (requireActivity().getSystemService(Context.NFC_SERVICE) as NfcManager?)?.defaultAdapter }
    private lateinit var nfcText : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        nfcText = binding.textNFC

        nfcText.text = if(adapter != null) "Очко" else "Ne o4ko"

        binding.button.setOnClickListener {
            findNavController().navigate(actionDestMainToDestEvents())
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        enableNfcForegroundDispatch()
    }

    private fun enableNfcForegroundDispatch() {
        try {
            val intent = Intent(requireContext(), javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            val nfcPendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0)
            adapter?.enableForegroundDispatch(requireActivity(), nfcPendingIntent, null, null)
        } catch (ex: IllegalStateException) {
            Timber.e(ex, "Error enabling NFC foreground dispatch")
        }
    }

    override fun onPause() {
        disableNfcForegroundDispatch()
        super.onPause()
    }

    private fun disableNfcForegroundDispatch() {
        try {
            adapter?.disableForegroundDispatch(requireActivity())
        } catch (ex: IllegalStateException) {
            Timber.e(ex, "Error disabling NFC foreground dispatch")
        }
    }

}