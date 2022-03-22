package com.communication.pingyi.ui.login.welcome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.communication.lib_http.base.MMKVTool
import com.communication.pingyi.activity.MainActivity


/**
 * Created by LG
 * on 2022/3/14  14:09
 * Description：
 */
class WelcomeFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (MMKVTool.getUsername().isBlank() || MMKVTool.getPassword().isBlank()){
            navigateToLogin()
        }else{
            goToMainActivity()
        }
    }

    private fun navigateToLogin() {
        val dir = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
        findNavController().navigate(dir)
    }



    private fun goToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        requireContext().startActivity(intent)
        requireActivity().finish()
    }
}