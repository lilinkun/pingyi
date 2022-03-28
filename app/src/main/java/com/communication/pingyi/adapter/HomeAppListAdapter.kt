package com.communication.pingyi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.adapters.ViewBindingAdapter.setClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_APP_CLICK
import com.communication.lib_http.httpdata.home.AppsItem
import com.communication.pingyi.databinding.ItemHomeAppsBinding
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2022/3/24  14:33
 * Description：
 */
class HomeAppListAdapter : ListAdapter<AppsItem, RecyclerView.ViewHolder>(AppItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BtiViewHolder(
            ItemHomeAppsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val app = getItem(position)
        (holder as BtiViewHolder).bind(app, position)
    }

    class BtiViewHolder(
        private val binding: ItemHomeAppsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AppsItem, position: Int) {
            binding.apply {
                appItem = item
                setClickListener {
                    if (checkDoubleClick()) {
                        LiveEventBus.get(EVENTBUS_APP_CLICK).post(item.key)
                    }
                }
                executePendingBindings()
            }
        }
    }
}

private class AppItemDiffCallback : DiffUtil.ItemCallback<AppsItem>() {

    override fun areItemsTheSame(oldItem: AppsItem, newItem: AppsItem): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: AppsItem, newItem: AppsItem): Boolean {
        return oldItem == newItem
    }
}
