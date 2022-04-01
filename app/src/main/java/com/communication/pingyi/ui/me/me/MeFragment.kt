package com.communication.pingyi.ui.me.me

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.communication.lib_core.PyAppDialog
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_CONTACT_SUCCESS
import com.communication.lib_core.tools.EVENTBUS_HOME_SUCCESS
import com.communication.lib_core.tools.EVENTBUS_LOGOUT_SUCCESS
import com.communication.lib_http.base.MMKVTool
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentMeBinding
import com.communication.pingyi.ui.main.MainFragmentDirections
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2022/3/17  11:22
 * Description：
 */
class MeFragment : BaseFragment<FragmentMeBinding>(){

    private val meViewModel by viewModel<MeViewModel>()

    override fun getLayoutResId(): Int = R.layout.fragment_me

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveEventBus.get(
            EVENTBUS_LOGOUT_SUCCESS,
            Boolean :: class.java
        ).observe(this,{
            if (lifecycle.currentState == Lifecycle.State.RESUMED){
                if (it){
                    logoutConfirm()
                }
            }
        })

        LiveEventBus.get(
            EVENTBUS_CONTACT_SUCCESS,
            Boolean::class.java
        ).observe(this,{
            if (it){
                meViewModel.getInfo()
            }
        })

    }




    override fun initView() {

        binding.apply {

            meLogout.setOnClickListener {

                if (checkDoubleClick()) {
                    logout()
                }

            }

            tvMeChangePwd.setOnClickListener {
                if (checkDoubleClick()){
                    val dir = MainFragmentDirections.actionMainFragmentToChangePwdFragment()
                    findNavController().navigate(dir)
                }
            }


            /*meInfo.setOnClickListener {

                if (checkDoubleClick()) {
                    val dir = MainFragmentDirections.actionMainFragmentToPersonInfoFragment()
                    findNavController().navigate(dir)
                }

            }*/

        }

    }

    override fun observeViewModels() {

        with(meViewModel){
            isLoading.observe(viewLifecycleOwner){
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }

            personInfo.observe(viewLifecycleOwner){
                it?.let {
                    binding.apply {
                        includeInfo.infoPhone.setContent(it.phonenumber)
                        includeInfo.infoOrganization.setContent(it.dept.deptName)
                        if(it.roles.size > 0) {
                            includeInfo.infoJob.setContent(it.roles[0].roleName)
                        }
                        tvUsername.setText(it.userName)
                        tvPersonal.setText(it.userName.substring(0, 1))
                    }
                }
            }

        }

    }


    /**
     * 退出登陆
     */
    private fun logout() {
        PyAppDialog(requireContext())
            .setSingle(false)
            .canCancel(true)
            .setTitle(resources.getString(R.string.me_logout))
            .setMessage(resources.getString(R.string.me_logout_confirm))
            .setPositive(resources.getString(R.string.sure))
            .setPositiveCallBack {
                meViewModel.logout()
            }
            .show()
    }


    private fun logoutConfirm() {
        MMKVTool.clearAll()
        requireActivity().finish()
    }

}