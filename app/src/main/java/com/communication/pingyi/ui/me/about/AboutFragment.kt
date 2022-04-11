package com.communication.pingyi.ui.me.about

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.communication.lib_core.tools.Utils
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aViewModel.getVersion()
    }

    override fun initView() {

        binding.apply {
            tvAboutVersion.text = "Version " + context?.let { Utils.getVersionName(it) }

            tvAboutTitle.setOnClickListener {

                findNavController().navigateUp()

            }

//            tvMeAbout.showCircle(true)

        }

    }

    override fun observeViewModels() {
    }
}