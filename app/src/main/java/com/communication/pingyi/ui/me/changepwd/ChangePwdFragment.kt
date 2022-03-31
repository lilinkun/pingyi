package com.communication.pingyi.ui.me.changepwd

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_CHANGE_PASSWORD_SUCCESS
import com.communication.lib_http.base.MMKVTool
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentChangepwdBinding
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2022/3/30  22:48
 * Description：
 */
class ChangePwdFragment : BaseFragment<FragmentChangepwdBinding>(){

    override fun getLayoutResId(): Int = R.layout.fragment_changepwd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveEventBus.get(
            EVENTBUS_CHANGE_PASSWORD_SUCCESS,
            Boolean::class.java
        ).observe(this,{
            if (checkDoubleClick()){
                logoutConfirm()
            }
        })


    }

    override fun initView() {

        binding.apply {

            llBack.setOnClickListener {
                if (checkDoubleClick()){
                    findNavController().navigateUp()
                }
            }

            tvModifySave.setOnClickListener {
                if (checkDoubleClick())
                {}
            }


        }

    }

    override fun observeViewModels() {

    }



    private fun logoutConfirm() {
        MMKVTool.clearAll()
        requireActivity().finish()
    }


}