package com.communication.pingyi.ui.message

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.communication.lib_core.RecycleViewDivider
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_MESSAGE_ITEM_CLICK
import com.communication.lib_core.tools.EVENTBUS_UNREAD_MESSAGE
import com.communication.pingyi.R
import com.communication.pingyi.adapter.MessageAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentMessageBinding
import com.jeremyliao.liveeventbus.LiveEventBus
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2022/3/16  16:37
 * Description：
 */
class MessageFragment : BaseFragment<FragmentMessageBinding>(), OnRefreshListener {

    val mViewModel : MessageViewModel by viewModel<MessageViewModel>()

    private val messageAdapter = MessageAdapter()

    override fun getLayoutResId(): Int = R.layout.fragment_message

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.getMessageList()

        LiveEventBus.get(
            EVENTBUS_MESSAGE_ITEM_CLICK,
            String::class.java
        ).observe(this,{
            mViewModel.readOnlyMessage(it)
        })

    }


    override fun initView() {
        binding.apply {

            rvMessage.adapter = messageAdapter

            context?.let { rvMessage.addItemDecoration(
                RecycleViewDivider(it,
                    LinearLayoutManager.VERTICAL)
            ) }

            ptMessageMain.setIconOnClick {
                if (checkDoubleClick()) {
                    mViewModel.readAllMessage()
                }
            }

        }

        binding.refreshLayout.setEnableRefresh(true)
        binding.refreshLayout.setOnRefreshListener(this)
    }

    override fun observeViewModels() {

        with(mViewModel){
            messageList.observe(viewLifecycleOwner){
                messageAdapter.submitList(it)
                messageAdapter.notifyDataSetChanged()

                LiveEventBus.get(EVENTBUS_UNREAD_MESSAGE).post(it.size)

            }

            isLoading.observe(viewLifecycleOwner){
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh(200)
        mViewModel.getMessageList()
    }
}