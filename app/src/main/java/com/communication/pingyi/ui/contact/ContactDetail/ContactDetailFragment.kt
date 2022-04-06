package com.communication.pingyi.ui.contact.ContactDetail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentContactDetailBinding
import com.communication.pingyi.ext.pyToast
import org.koin.android.ext.android.bind

class ContactDetailFragment : BaseFragment<FragmentContactDetailBinding>(){

    override fun getLayoutResId(): Int = R.layout.fragment_contact_detail

    override fun initView() {

        val phone = arguments?.getString("phone")
        val username = arguments?.getString("username")
        val role = arguments?.getString("role")
        val dept = arguments?.getString("dept")


        binding.apply{
            if (phone != null) {
                includeInfo.infoPhone.setContent(phone)
            }
            if (dept != null) {
                includeInfo.infoOrganization.setContent(dept)
            }

            if (role != null) {
                includeInfo.infoJob.setContent(role)
            }
            includeInfo.infoPhone.setShowIcon(true)
            includeInfo.infoOrganization.setShowIcon(true)
            includeInfo.infoJob.setShowIcon(true)

            tvUsername.setText(username)
            tvPersonal.setText(username?.substring(0, 1))

            tvContactPhone.setOnClickListener {

                val intent = Intent()
                intent.action  =  Intent.ACTION_DIAL
                intent.data = Uri.parse("tel:$phone")
                startActivity(intent)
            }

            tvContactMessage.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SENDTO
                intent.data = Uri.parse("smsto:$phone")
                startActivity(intent)
            }

            tvContactCopy.setOnClickListener {
                val clipboard =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager;
                val clipData = ClipData.newPlainText("Label",phone)
                clipboard.setPrimaryClip(clipData)
                pyToast("已复制邮箱地址到剪贴板")
            }

            llTitle.setOnClickListener {
                findNavController().navigateUp()
            }

        }



    }

    override fun observeViewModels() {

    }
}