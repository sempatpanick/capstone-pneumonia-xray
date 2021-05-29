package com.sempatpanick.pneumoniaxray.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.sempatpanick.pneumoniaxray.MainActivity
import com.sempatpanick.pneumoniaxray.R
import com.sempatpanick.pneumoniaxray.core.data.Resource
import com.sempatpanick.pneumoniaxray.core.data.UserRepository
import com.sempatpanick.pneumoniaxray.core.domain.model.Doctor
import com.sempatpanick.pneumoniaxray.core.manager.SessionManager
import com.sempatpanick.pneumoniaxray.databinding.ActivityLoginBinding
import com.sempatpanick.pneumoniaxray.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userRepository: UserRepository

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val sesi = SessionManager(this)
        userRepository = UserRepository.getInstance(sesi)

        if (userRepository.isUserLogin()) {
            moveToMain()
        }

        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnLogin.id -> {
                setLogin()
            }
        }
    }

    private fun setLogin() {
        val username = binding.inputUsername.text.toString()
        val password = binding.inputPassword.text.toString()
        loginViewModel.getDoctor(username, password).observe(this, { doctor ->
            if (doctor != null) {
                when (doctor) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val dokter = Doctor(
                            doctor.data?.get(0)?.idDoctor,
                            doctor.data?.get(0)?.nama,
                            doctor.data?.get(0)?.username,
                            doctor.data?.get(0)?.password
                        )
                        userRepository.loginUser(dokter)
                        moveToMain()
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            doctor.message ?: "Oops.. Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun moveToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}