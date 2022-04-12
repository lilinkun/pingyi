package com.communication.pingyi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_MESSAGE_ITEM_CLICK
import com.communication.lib_http.httpdata.message.MessageBean
import com.communication.pingyi.databinding.ItemMessageBinding
import com.jeremyliao.liveeventbus.LiveEventBus
import com.communication.lib_core.R

class MessageAdapter : ListAdapter<MessageBean,RecyclerView.ViewHolder>(MessageDiffCallback()) {

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
        fun bind(item: MessageBean, position: Int) {
            binding.apply {
                message = item
                when(item.messageType){
                    0 -> { tvMessageType.setText("事件")
                        tvMessageType.setBackground(ContextCompat.getDrawable(root.context,R.mipmap.ic_message_event))}
                    1 -> {tvMessageType.setText("工单")
                    tvMessageType.setBackground(ContextCompat.getDrawable(root.context,R.mipmap.ic_message_orders))}
                    2 -> {tvMessageType.setText("养护")
                    tvMessageType.setBackground(ContextCompat.getDrawable(root.context,R.mipmap.ic_message_maintain))}
                }
                if(item.isRead == 0){
                    ivCircle.visibility = View.VISIBLE
                }else{
                    ivCircle.visibility = View.GONE
                }
                setClickListener {
                    if (checkDoubleClick()) {
                        LiveEventBus.get(EVENTBUS_MESSAGE_ITEM_CLICK).post(item.messageId)
                    }
                }
                executePendingBindings()
            }
        }
    }
}


private class MessageDiffCallback : DiffUtil.ItemCallback<MessageBean>() {
    override fun areItemsTheSame(oldItem: MessageBean, newItem: MessageBean): Boolean {
        return oldItem.messageId == newItem.messageId
    }

    override fun areContentsTheSame(
        oldItem: MessageBean,
        newItem: MessageBean
    ): Boolean {
        return oldItem == newItem
    }
}