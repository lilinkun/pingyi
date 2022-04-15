package com.communication.pingyi.ui.login.spalash

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.ui.login.welcome.WelcomeFragmentDirections
import com.jeremyliao.liveeventbus.LiveEventBus

class SpalashFragment : Fragment(){

    var isLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            LiveEventBus.get("name",String::class.java).observe(this,{
                isLogin = true
            })

            if(isLogin){
                navigateToLogin()
            }else {
                navigateToWelcome()
            }
        }


        private fun navigateToLogin() {
            val dir = SpalashFragmentDirections.actionSpalashFragmentToLoginFragment()
            findNavController().navigate(dir)
        }


        private fun navigateToWelcome(){
            val dir = SpalashFragmentDirections.actionSpalashFragmentToWelcomeFragment()
            findNavController().navigate(dir)
        }
}