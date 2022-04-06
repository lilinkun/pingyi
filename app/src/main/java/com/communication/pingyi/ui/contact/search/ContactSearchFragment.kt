package com.communication.pingyi.ui.contact.search

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentSearchBinding
import com.communication.pingyi.ext.hideKeyboard
import com.communication.pingyi.ext.pyToast

/**
 * Created by LG
 * on 2022/4/6  15:08
 * Description：
 */
class ContactSearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_search

    override fun initView() {

        binding.apply {
            ivContactSearchBack.setOnClickListener{
                hideKeyboard(it)
                findNavController().navigateUp()
            }

            etContactSearch.apply {
                addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                    }

                })

                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    }
                    false
                }
            }
        }

    }

    override fun observeViewModels() {
    }
}