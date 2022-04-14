package com.communication.pingyi.ui.home

import android.os.Bundle
import android.view.View
import com.communication.lib_core.tools.EVENTBUS_HOME_APPS_SUCCESS
import com.communication.lib_http.base.MMKVTool
import com.communication.lib_http.httpdata.home.AppsItem
import com.communication.pingyi.R
import com.communication.pingyi.adapter.HomeAppListAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentHomeBinding
import com.communication.pingyi.ext.pyToast
import com.jeremyliao.liveeventbus.LiveEventBus
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2022/3/16  17:23
 * Description：
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnRefreshListener {

    private val mViewModel by viewModel<AppsViewModel>()


    private val mAppListAdapter = HomeAppListAdapter()


    override fun getLayoutResId(): Int = R.layout.fragment_home


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.getHomeFlow()
        mViewModel.getHomeAppsList()

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
        binding.refreshLayout.setEnableRefresh(true)
        binding.refreshLayout.setOnRefreshListener(this)
        
    }

    override fun observeViewModels() {
        with(mViewModel){
            appsLiveData.observe(viewLifecycleOwner){
                val appsItem : MutableList<AppsItem>? = appsLiveData.value?.children
                mAppListAdapter.submitList(appsItem)
                mAppListAdapter.notifyDataSetChanged()
            }

            homeFlow.observe(viewLifecycleOwner){
                val homeFlowBean = homeFlow.value
                binding.homeFlow = homeFlowBean
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
    }



    private fun initHomeAppComplete() {

//        mAppListAdapter

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh(200)
        mViewModel.getHomeFlow()
        mViewModel.getHomeAppsList()

    }


}