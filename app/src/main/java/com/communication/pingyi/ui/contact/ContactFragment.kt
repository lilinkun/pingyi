package com.communication.pingyi.ui.contact

import android.os.Bundle
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentContactsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2022/3/17  14:11
 * Description：
 */
class ContactFragment : BaseFragment<FragmentContactsBinding>(){

    val mViewModel : ContactViewModel by viewModel<ContactViewModel>()

    override fun getLayoutResId(): Int = R.layout.fragment_contacts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.getContactList()

    }

    override fun initView() {

    }

    override fun observeViewModels() {
    }

}