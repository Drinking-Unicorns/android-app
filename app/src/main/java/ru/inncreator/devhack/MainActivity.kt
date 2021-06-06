package ru.inncreator.devhack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.inncreator.devhack.remote.RemoteConnectionController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RemoteConnectionController.initQueue(this)
    }
}