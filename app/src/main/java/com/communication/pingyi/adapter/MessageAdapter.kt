package com.communication.pingyi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_MESSAGE_ITEM_CLICK
import com.communication.lib_http.httpdata.message.EventMessageBean
import com.communication.pingyi.databinding.ItemMessageBinding
import com.jeremyliao.liveeventbus.LiveEventBus

class MessageAdapter : ListAdapter<EventMessageBean,RecyclerView.ViewHolder>(MessageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PyViewHolder(
            ItemMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val app = getItem(position)
        (holder as PyViewHolder).bind(app, position)
    }

    class PyViewHolder(
        private val binding: ItemMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventMessageBean, position: Int) {
            binding.apply {
                messageItem = item
                setClickListener {
                    if (checkDoubleClick()) {
                        LiveEventBus.get(EVENTBUS_MESSAGE_ITEM_CLICK).post(item.eventId)
                    }
                }
                executePendingBindings()
            }
        }
    }
}


private class MessageDiffCallback : DiffUtil.ItemCallback<EventMessageBean>() {
    override fun areItemsTheSame(oldItem: EventMessageBean, newItem: EventMessageBean): Boolean {
        return oldItem.eventId == newItem.eventId
    }

    override fun areContentsTheSame(
        oldItem: EventMessageBean,
        newItem: EventMessageBean
    ): Boolean {
        return oldItem == newItem
    }
}