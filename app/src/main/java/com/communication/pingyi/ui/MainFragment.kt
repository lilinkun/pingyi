package com.communication.pingyi.ui

import com.communication.pingyi.R
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentMainBinding

/**
 * Created by LG
 * on 2022/3/16  17:42
 * Description：
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_main

    override fun initView() {
        val viewPager = binding.viewPager
        val bottomNavBar = binding.bottomNavBar
        viewPager.adapter = MainPagerAdapter(this)
        viewPager.currentItem = 0
        viewPager.offscreenPageLimit = 4
        viewPager.isUserInputEnabled = false//禁止滑动

        bottomNavBar.setOnNavigationItemSelectedListener {
            var index = ITEM_HOME
            index = when (it.itemId) {
                R.id.nav_home -> ITEM_HOME
                R.id.nav_message -> ITEM_MESSAGE
//                R.id.nav_train -> ITEM_TRAIN
                R.id.nav_me -> ITEM_ME
                else -> ITEM_HOME
            }
            viewPager.setCurrentItem(index, false)
            true
        }

    }

    override fun observeViewModels() {
    }
}