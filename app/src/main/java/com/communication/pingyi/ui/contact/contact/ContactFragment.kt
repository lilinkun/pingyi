package com.communication.pingyi.ui.contact.contact

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.communication.lib_core.tools.EVENTBUS_CONTACT_CLICK
import com.communication.lib_core.tools.EVENTBUS_CONTACT_SUCCESS
import com.communication.lib_core.tools.EVENTBUS_HOME_SUCCESS
import com.communication.lib_http.httpdata.contact.ContactItem
import com.communication.pingyi.R
import com.communication.pingyi.adapter.ContactAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentContactsBinding
import com.communication.pingyi.ui.main.MainFragmentDirections
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2022/3/17  14:11
 * Description：
 */
class ContactFragment : BaseFragment<FragmentContactsBinding>(){

    val mViewModel : ContactViewModel by viewModel<ContactViewModel>()
    lateinit var orgList : MutableList<ContactItem>

    private val mContactAdapter = ContactAdapter()


    override fun getLayoutResId(): Int = R.layout.fragment_contacts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveEventBus.get(
            EVENTBUS_HOME_SUCCESS,
            Boolean::class.java
        ).observe(this,{
            if(it) {
                mViewModel.getContactList()
            }
        })

        LiveEventBus.get(
            EVENTBUS_CONTACT_CLICK,
            Int::class.java
        ).observe(this,{
            if (isActive()) {
                val dir = MainFragmentDirections.actionMainFragmentToOrgListFragment(it.toString())
                findNavController().navigate(dir)
            }
        })

    }

    override fun initView() {

        binding.apply {

            rvContact.adapter = mContactAdapter


        }

    }

    override fun observeViewModels() {
        with(mViewModel){
            org_list.observe(viewLifecycleOwner){
                orgList = it
                mContactAdapter.submitList(orgList)
                mContactAdapter.notifyDataSetChanged()

                LiveEventBus.get(EVENTBUS_CONTACT_SUCCESS).post(true)
            }
        }
    }

}