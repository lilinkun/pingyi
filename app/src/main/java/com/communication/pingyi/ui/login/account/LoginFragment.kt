package com.communication.pingyi.ui.login.account

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.view.View
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.communication.lib_core.checkDoubleClick
import com.communication.lib_core.tools.EVENTBUS_LOGIN_SUCCESS
import com.communication.lib_core.tools.EVENTBUS_TOKEN_SUCCESS
import com.communication.lib_core.tools.Utils
import com.communication.lib_http.api.SERVER_BASE_URL
import com.communication.lib_http.httpdata.login.LoginInfo
import com.communication.pingyi.R
import com.communication.pingyi.activity.MainActivity
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentLoginBinding
import com.communication.pingyi.tools.PUBLICKEY
import com.communication.pingyi.tools.RSAUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

/**
 * Created by LG
 * on 2022/3/10  16:14
 * Description：
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    val mLoginViewModel : LoginViewModel by sharedViewModel<LoginViewModel>()

    lateinit var mLoginInfo : LoginInfo;
    lateinit var sign : String;

    override fun getLayoutResId(): Int = R.layout.fragment_login


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveEventBus.get(
            EVENTBUS_TOKEN_SUCCESS,
            Boolean::class.java
        ).observe(this,{
            if (lifecycle.currentState == Lifecycle.State.RESUMED){
                if (it){
                    mLoginViewModel.createOrGetAuthorization(mLoginInfo)
                }
            }
        })

        LiveEventBus.get(
            EVENTBUS_LOGIN_SUCCESS,
            Boolean::class.java
        ).observe(this,{

            if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                if(it) {
                    goToMainActivity()
                }
            }
        }
        )

    }

    private fun goToMainActivity() {
        val intent = Intent(requireContext(),MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }


    override fun initView() {


        binding.let {

//            showVcode()

            it.btnLogin.setOnClickListener{

                if (checkDoubleClick(1000)) {
                    clickLogin()
                }

            }

            it.ivVerificationCode.setOnClickListener{
                if (checkDoubleClick(1000)){

//                    showVcode()
                }
            }

        }

    }

    private fun clickLogin() {
        if (isActive()) {
            checkLogin()
        }
    }

    private fun showVcode(){

        sign = Date().time.toString() + ""

        Glide.with(this).load(SERVER_BASE_URL+"/code?sign=" + sign).into(binding.ivVerificationCode)
    }

    override fun observeViewModels() {

        with(mLoginViewModel) {
            isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

    }


    private fun checkLogin() {
        binding.apply {
            if (etUsername.text!!.isBlank()) {
                return
            }
            if (etPassword.text!!.isBlank()) {
                return
            }
            if (etVerificationCode.text!!.isBlank()) {
                return
            }
            val uname = etUsername.text.toString().trim()
            val pwd = etPassword.text.toString().trim()
            val code = etVerificationCode.text.toString().trim()

            val psd = Base64.encodeToString(
                RSAUtils.encryptByPublicKey(pwd, PUBLICKEY),
                NO_WRAP
            )

//            val psd = Base64.encodeToString(pwd.toByteArray(charset("utf-8")), NO_WRAP)
            val uuid = ""
            val clientType = "1"


            mLoginInfo = LoginInfo(code, uname,psd,clientType,uuid)

            LiveEventBus.get(EVENTBUS_TOKEN_SUCCESS).post(true)
        }
    }

}