package com.communication.pingyi.ui.home

import android.os.Bundle
import android.view.View
import com.communication.lib_core.tools.EVENTBUS_CONTACT_SUCCESS
import com.communication.lib_core.tools.EVENTBUS_HOME_APPS_SUCCESS
import com.communication.lib_core.tools.EVENTBUS_HOME_SUCCESS
import com.communication.lib_http.httpdata.home.AppsItem
import com.communication.pingyi.R
import com.communication.pingyi.adapter.HomeAppListAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentHomeBinding
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2022/3/16  17:23
 * Description：
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(){

    private val mViewModel by viewModel<AppsViewModel>()


    private val mAppListAdapter = HomeAppListAdapter()


    override fun getLayoutResId(): Int = R.layout.fragment_home


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveEventBus.get(
            EVENTBUS_HOME_APPS_SUCCESS,
            String::class.java
        ).observe(this,{

            initHomeAppComplete()

        })

    }
    override fun initView() {

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            appList.adapter = mAppListAdapter
        }
    }

    override fun observeViewModels() {
        with(mViewModel){
            appsLiveData.observe(viewLifecycleOwner){
                val appsItem : MutableList<AppsItem>? = appsLiveData.value?.children
                mAppListAdapter.submitList(appsItem)
                mAppListAdapter.notifyDataSetChanged()
                LiveEventBus.get(EVENTBUS_HOME_SUCCESS).post(true)
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

    override fun onStart() {
        super.onStart()
        mViewModel.getHomeAppsList()
    }



    private fun initHomeAppComplete() {

//        mAppListAdapter

    }


}