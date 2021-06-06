package ru.inncreator.devhack.fragments.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

class EventsFragmentsViewModel : ViewModel(), KoinComponent {

    val visibilityProgressBar = MutableLiveData(true)
    init {

    }
}