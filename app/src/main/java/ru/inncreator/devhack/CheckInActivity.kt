package ru.inncreator.devhack

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import timber.log.Timber

class CheckInActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)
    }

    public override fun onPause() {
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
        val intent = intent
        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action) {
            val id: ByteArray = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: throw Throwable()
            Toast.makeText(this, "Id = $id", Toast.LENGTH_LONG).show()
            Timber.i("Id = $id")
        }
    }

}