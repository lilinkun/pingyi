package com.communication.pingyi.ui.contact.orglist

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.communication.lib_core.IClickOnlyCallBack
import com.communication.lib_core.tools.EVENTBUS_CONTACT_USER_CLICK
import com.communication.lib_http.httpdata.contact.ContactItem
import com.communication.lib_http.httpdata.contact.ContactUserBean
import com.communication.lib_http.httpdata.me.PersonInfoBean
import com.communication.pingyi.R
import com.communication.pingyi.adapter.ContactAdapter
import com.communication.pingyi.adapter.ContactUserAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentOrglistBinding
import com.communication.pingyi.ui.contact.contact.ContactViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2022/4/1  10:24
 * Description：
 */
class OrgListFragment : BaseFragment<FragmentOrglistBinding>(){

    private val args: OrgListFragmentArgs by navArgs()
    val mViewModel : ContactViewModel by viewModel<ContactViewModel>()

    lateinit var orgList : MutableList<ContactItem>
    lateinit var userList : MutableList<ContactUserBean>

    val mContactItemAdapter = ContactAdapter()
    val mContactUserAdapter = ContactUserAdapter()

    override fun getLayoutResId(): Int = R.layout.fragment_orglist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.getContactUser(args.id)

        LiveEventBus.get(
            EVENTBUS_CONTACT_USER_CLICK,
            PersonInfoBean::class.java
        ).observe(this,{

        })


    }

    override fun initView() {

        binding.apply{
            rvOrglist.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            rvContactItem.adapter = mContactItemAdapter
            rvContactUserItem.adapter = mContactUserAdapter

            backTitle.setBackOnClick(IClickOnlyCallBack {
                findNavController().navigateUp()
            })

        }


    }

    override fun observeViewModels() {
        with(mViewModel){
            org_user.observe(viewLifecycleOwner){

                it.trees?.apply {
                    orgList = it.trees
                    mContactItemAdapter.submitList(orgList)
                    mContactItemAdapter.notifyDataSetChanged()
                }

                it.users?.apply {
                    userList = it.users
                    userList?.let {
                        mContactUserAdapter.submitList(it)
                        mContactUserAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}