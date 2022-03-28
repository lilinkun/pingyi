package com.communication.pingyi.ui.message

import com.communication.pingyi.R
import com.communication.pingyi.adapter.MessageAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentMessageBinding

/**
 * Created by LG
 * on 2022/3/16  16:37
 * Description：
 */
class MessageFragment : BaseFragment<FragmentMessageBinding>(){


    val messageAdapter = MessageAdapter()

    override fun getLayoutResId(): Int = R.layout.fragment_message

    override fun initView() {
        binding.apply {

            rvMessage.adapter = messageAdapter


        }

    }

    override fun observeViewModels() {
    }
}