package com.communication.pingyi.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import com.communication.lib_core.PyMessageRed
import com.communication.lib_core.tools.EVENTBUS_APP_CLICK
import com.communication.lib_http.httpdata.message.EventMessageBean
import com.communication.pingyi.R
import com.communication.pingyi.activity.WebviewActivity
import com.communication.pingyi.adapter.*
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2022/3/16  17:42
 * Description：
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveEventBus.get(
            EVENTBUS_APP_CLICK,
            String::class.java
        ).observe(this,{ key->
            key?.let {
                goToWebActivity()
            }
        })



    }

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
                R.id.nav_contacts -> ITEM_CONTACTS
                R.id.nav_message -> ITEM_MESSAGE
                R.id.nav_me -> ITEM_ME
                else -> ITEM_HOME
            }
            viewPager.setCurrentItem(index, false)
            true
        }

    }

    override fun observeViewModels() {
    }

    private fun initMessage(list: MutableList<EventMessageBean>?) {
        var isRead = true
        var count = 0
        list?.forEach { item ->
            if (!item.isRead) {
                isRead = false
                count += 1
            }
        }

        binding.bottomNavBar.children.forEach { menuView ->
            if (menuView is BottomNavigationMenuView) {
                menuView.forEachIndexed { index, itemView ->
                    if (index == 1) {
                        if (itemView is BottomNavigationItemView) {
                            itemView.forEach { v ->
                                if (v is PyMessageRed) {
                                    itemView.removeView(v)
                                }
                            }
                        }
                    }
                }
            }
        }
        if (count != 0) {
            binding.bottomNavBar.children.forEach { menuView ->
                if (menuView is BottomNavigationMenuView) {
                    menuView.forEachIndexed { index, itemView ->
                        if (index == 1) {
                            if (itemView is BottomNavigationItemView) {
                                val t = PyMessageRed(requireContext())
                                t.setCount(count.toString())
                                itemView.addView(t)
                            }
                        }
                    }
                }
            }
        }
    }


    private fun goToWebActivity() {
        val intent = Intent(requireContext(), WebviewActivity::class.java)
        startActivity(intent)
    }

}