package ru.inncreator.devhack.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import ru.inncreator.devhack.R
import ru.inncreator.devhack.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val vm: MainFragmentViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}