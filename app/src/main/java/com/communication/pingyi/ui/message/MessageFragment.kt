package com.communication.pingyi.ui.message

import android.os.Bundle
import com.communication.lib_core.checkDoubleClick
import com.communication.pingyi.R
import com.communication.pingyi.adapter.MessageAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.base.BaseViewModel
import com.communication.pingyi.databinding.FragmentMessageBinding
import com.communication.pingyi.ext.pyToast
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2022/3/16  16:37
 * Description：
 */
class MessageFragment : BaseFragment<FragmentMessageBinding>(){

    val mViewModel : MessageViewModel by viewModel<MessageViewModel>()

    val messageAdapter = MessageAdapter()

    override fun getLayoutResId(): Int = R.layout.fragment_message

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        LiveEventBus.get(EVENTBUS_CONTACT_SUCCESS).observe(this,{
//            mViewModel.getMessage()
//        })

    }


    override fun initView() {
        binding.apply {

            rvMessage.adapter = messageAdapter
            ptMessageMain.setIconOnClick {
                if (checkDoubleClick()) {
                    pyToast("asfdasf")
                }
            }

        }

    }

    override fun observeViewModels() {


    }
}