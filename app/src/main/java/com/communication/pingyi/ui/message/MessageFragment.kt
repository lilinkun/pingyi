package com.communication.pingyi.ui.message

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.communication.lib_core.PyAppDialog
import com.communication.lib_core.RecycleViewDivider
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_MESSAGE_CLICK
import com.communication.lib_core.tools.EVENTBUS_MESSAGE_ITEM_CLICK
import com.communication.lib_core.tools.EVENTBUS_UNREAD_MESSAGE
import com.communication.lib_http.httpdata.message.MessageBean
import com.communication.pingyi.R
import com.communication.pingyi.adapter.MessageAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentMessageBinding
import com.communication.pingyi.ext.pyToast
import com.communication.pingyi.ext.pyToastShort
import com.jeremyliao.liveeventbus.LiveEventBus
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2022/3/16  16:37
 * Description：
 */
class MessageFragment : BaseFragment<FragmentMessageBinding>() , OnRefreshListener{

    val mViewModel : MessageViewModel by viewModel<MessageViewModel>()

    private val messageAdapter = MessageAdapter()
    private lateinit var userId : String;

    var unReadCount = 0

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
                    if (unReadCount != 0) {
                        userId?.let {
                            allReadMessage(it)
                        }
                    }else{
                        pyToastShort(resources.getString(R.string.no_read_message))
                    }

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

                it?.apply {
                    if (it.size > 0) {
                        userId = it.get(0).userId
                        unReadCount = 0

                        for (messageBean: MessageBean in it) {
                            if (messageBean.isRead == 0) {
                                unReadCount++
                            }
                        }
                        LiveEventBus.get(EVENTBUS_UNREAD_MESSAGE).post(unReadCount)
                    }


                }


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

    /**
     * 消息全设置为已读
     */
    private fun allReadMessage(userId : String) {
        PyAppDialog(requireContext())
            .setSingle(false)
            .canCancel(true)
            .setTitle(resources.getString(R.string.main_message))
            .setMessage(resources.getString(R.string.all_read_hint))
            .setPositive(resources.getString(R.string.sure))
            .setPositiveCallBack {
                mViewModel.readAllMessage(userId)
            }
            .show()
    }
}