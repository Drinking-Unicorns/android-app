package ru.inncreator.devhack.fragments.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import ru.inncreator.devhack.R
import ru.inncreator.devhack.databinding.FragmentEventsBinding
import ru.inncreator.devhack.remote.RemoteConnectionController
import ru.inncreator.devhack.utils.SliderTransformer
import ru.inncreator.devhack.models.Event
import timber.log.Timber

class EventsFragment : Fragment() {

    private val vm: EventsFragmentsViewModel by inject()
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEventsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false)
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        adapter = EventsAdapter{}
        binding.swiper.adapter = adapter
        binding.swiper.offscreenPageLimit = 3
        binding.swiper.setPageTransformer(SliderTransformer(3))

        getAllEvents()


        return binding.root
    }


    // Request a string response from the provided URL.
    private fun getAllEvents() {
        val url = "http://94.26.248.9/events/Info/get-all-events"
        val request = StringRequest(
            Request.Method.GET, url,
            {
               adapter.submitList(toEvent(it))
                vm.visibilityProgressBar.postValue(false)
            },
            {
                Timber.e(it, " Пошло по пизде")
            })
        RemoteConnectionController.queue.add(request)
    }

    private fun toEvent(stringJson: String): List<Event> {
        val gson = Gson()
        val objectList = gson.fromJson(stringJson, Array<Event>::class.java).asList()
        Timber.i("Список ивентов с сервера = $objectList")
        return objectList
    }
}