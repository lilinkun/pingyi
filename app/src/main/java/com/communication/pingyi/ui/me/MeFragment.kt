package com.communication.pingyi.ui.me

import android.view.View
import androidx.lifecycle.Lifecycle
import com.communication.lib_core.PyAppDialog
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_LOGOUT_SUCCESS
import com.communication.lib_http.base.MMKVTool
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentMeBinding
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

    override fun initView() {

        LiveEventBus.get(
            EVENTBUS_LOGOUT_SUCCESS,
            Boolean :: class.java).observe(this,{
            if (lifecycle.currentState == Lifecycle.State.RESUMED){
                if (it){
                    logoutConfirm()
                }
            }
        })


        binding.apply {

            logout.setOnClickListener {

                if (checkDoubleClick()) {
                    logout()
                }

            }

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
        }

    }


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