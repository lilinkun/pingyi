package com.communication.pingyi.ui.me.about

import androidx.fragment.app.viewModels
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentAboutBinding

/**
 * Created by LG
 * on 2022/4/8  14:43
 * Description：
 */
class AboutFragment : BaseFragment<FragmentAboutBinding>() {

    val aViewModel : AboutViewModel by viewModels<AboutViewModel>()

    override fun getLayoutResId(): Int = R.layout.fragment_about

    override fun initView() {

    }

    override fun observeViewModels() {
    }
}