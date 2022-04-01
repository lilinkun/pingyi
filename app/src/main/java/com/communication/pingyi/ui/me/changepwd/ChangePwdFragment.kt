package com.communication.pingyi.ui.me.changepwd

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_CHANGE_PASSWORD_SUCCESS
import com.communication.lib_core.tools.EVENTBUS_TOAST_STRING
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
                if (checkDoubleClick()){


                    if (evPsdOldValue.text!!.isBlank()) {
                        LiveEventBus.get(EVENTBUS_TOAST_STRING).post("请输入旧密码")
                        return@setOnClickListener
                    }
                    if (evPsdNewValue.text!!.isBlank()) {
                        LiveEventBus.get(EVENTBUS_TOAST_STRING).post("请输入新密码")
                        return@setOnClickListener
                    }
                    if (evPsdSureNewValue.text!!.isBlank()) {
                        LiveEventBus.get(EVENTBUS_TOAST_STRING).post("请确认新密码")
                        return@setOnClickListener
                    }

                    if (evPsdNewValue.text != evPsdSureNewValue.text){
                        LiveEventBus.get(EVENTBUS_TOAST_STRING).post("两次新密码不一致")
                        return@setOnClickListener
                    }




                }
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