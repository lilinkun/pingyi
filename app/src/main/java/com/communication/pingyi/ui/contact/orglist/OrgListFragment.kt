package com.communication.pingyi.ui.contact.orglist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.communication.lib_core.RecycleViewDivider
import com.communication.lib_core.tools.EVENTBUS_CONTACT_CLICK
import com.communication.lib_core.tools.EVENTBUS_CONTACT_USER_CLICK
import com.communication.lib_http.httpdata.contact.ContactItem
import com.communication.lib_http.httpdata.contact.ContactUserBean
import com.communication.pingyi.R
import com.communication.pingyi.adapter.ContactAdapter
import com.communication.pingyi.adapter.ContactUserAdapter
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentOrglistBinding
import com.communication.pingyi.ext.pyToast
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
            ContactUserBean::class.java
        ).observe(this,{
            val dir = OrgListFragmentDirections.actionOrgListFragmentToContactDetailFragment(it.userName,it.phoneNumber,it.postJob,it.dept.deptName)
            findNavController().navigate(dir)
        })


        LiveEventBus.get(
            EVENTBUS_CONTACT_CLICK,
            ContactItem::class.java
        ).observe(this,{
            if (isActive()) {
                if (it.size > 0) {
                    mViewModel.getContactUser(it.id.toString())
                }else{
                    pyToast("部门暂未开通")
                }
            }
        })


    }

    override fun initView() {

        binding.apply{

            rvContactItem.adapter = mContactItemAdapter
            rvContactUserItem.adapter = mContactUserAdapter


            context?.let { rvContactItem.addItemDecoration(
                RecycleViewDivider(it,
                    LinearLayoutManager.VERTICAL)
            ) }


            context?.let { rvContactUserItem.addItemDecoration(
                RecycleViewDivider(it,
                    LinearLayoutManager.VERTICAL)
            ) }

            tvContactTitle.setText(args.title)
            tvContactTitle.setOnClickListener {
                findNavController().navigateUp()
            }

            tvContactSearch.setOnClickListener {
                val dir = OrgListFragmentDirections.actionOrgListFragmentToContactSearchFragment()
                findNavController().navigate(dir)
            }

        }


    }

    override fun observeViewModels() {
        with(mViewModel){
            org_user.observe(viewLifecycleOwner){



                org_user.value?.apply {
                    if (it.trees != null){
                        orgList = it.trees
                        binding.rvContactItem.visibility = View.VISIBLE
                        mContactItemAdapter.submitList(orgList)
                        mContactItemAdapter.notifyDataSetChanged()
                    }else{
                        binding.rvContactItem.visibility = View.GONE
                    }

                    if (it.users != null) {
                        userList = it.users
                        binding.rvContactUserItem.visibility = View.VISIBLE
                        mContactUserAdapter.submitList(userList)
                        mContactUserAdapter.notifyDataSetChanged()
                    }else{
                        binding.rvContactUserItem.visibility = View.GONE
                    }
                }



                isLoading.observe(viewLifecycleOwner) {
                    if (it) {
                        binding.progressBar.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }
}