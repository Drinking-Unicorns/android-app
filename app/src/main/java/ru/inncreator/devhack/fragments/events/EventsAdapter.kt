package ru.inncreator.devhack.fragments.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.inncreator.devhack.databinding.ItemEventBinding
import ru.inncreator.devhack.models.Event
import ru.inncreator.devhack.models.dateToString
import ru.inncreator.devhack.models.typeToString

class EventsAdapter(
    private val onItemClick: () -> Unit
) :
    ListAdapter<Event, EventsAdapter.EventHolder>(EventItemDiffCallback()) {


    private class EventItemDiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Event, newItem: Event) =
            oldItem.date == newItem.date
    }

    class EventHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Event) {
            binding.apply {
                eventName.text = item.name
                eventType.text = item.typeToString().toUpperCase()
                eventDate.text = item.dateToString()
                eventTypeTwo.text = item.typeToString().toUpperCase()
                eventNameTwo.text = item.name
                eventDateTwo.text = item.dateToString()

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        return EventHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}