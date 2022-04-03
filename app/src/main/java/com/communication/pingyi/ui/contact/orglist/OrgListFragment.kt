package com.communication.pingyi.ui.contact.orglist

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.communication.lib_core.IClickOnlyCallBack
import com.communication.lib_core.tools.EVENTBUS_CONTACT_CLICK
import com.communication.lib_core.tools.EVENTBUS_CONTACT_USER_CLICK
import com.communication.lib_http.httpdata.contact.ContactItem
import com.communication.lib_http.httpdata.contact.ContactUserBean
import com.communication.lib_http.httpdata.me.PersonInfoBean
import com.communication.pingyi.R
import com.communication.pingyi.adapter.ContactAdapter
import com.communication.pingyi.adapter.ContactUserAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentOrglistBinding
import com.communication.pingyi.ext.pyToast
import com.communication.pingyi.ui.contact.contact.ContactViewModel
import com.communication.pingyi.ui.main.MainFragmentDirections
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
            ContactUserBean::class.java
        ).observe(this,{
            val dir = OrgListFragmentDirections.actionOrgListFragmentToContactDetailFragment(it.userName,it.phoneNumber,it.postJob,it.dept.deptName)
            findNavController().navigate(dir)
        })


        LiveEventBus.get(
            EVENTBUS_CONTACT_CLICK,
            Int::class.java
        ).observe(this,{
            if (isActive()) {
                pyToast("底下没有人了")
            }
        })


    }

    override fun initView() {

        binding.apply{

            rvContactItem.adapter = mContactItemAdapter
            rvContactUserItem.adapter = mContactUserAdapter

            tvContactTitle.setText(args.title)
            tvContactTitle.setOnClickListener {
                findNavController().navigateUp()
            }

        }


    }

    override fun observeViewModels() {
        with(mViewModel){
            org_user.observe(viewLifecycleOwner){

                org_user.value?.trees?.apply {
                    orgList = it.trees
                    mContactItemAdapter.submitList(orgList)
                    mContactItemAdapter.notifyDataSetChanged()
                }

                org_user.value?.users?.apply {
                    userList = this
                    userList?.let {
                        mContactUserAdapter.submitList(it)
                        mContactUserAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}