package com.alvex.pneumoniaxray.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import com.alvex.pneumoniaxray.main.MainActivity
import com.alvex.pneumoniaxray.R
import com.alvex.pneumoniaxray.core.data.Resource
import com.alvex.pneumoniaxray.core.data.UserRepository
import com.alvex.pneumoniaxray.core.domain.model.Login
import com.alvex.pneumoniaxray.core.manager.SessionManager
import com.alvex.pneumoniaxray.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable

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

        setUp()

        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnLogin.id -> setLogin()
        }
    }

    @SuppressLint("CheckResult")
    private fun setUp() {

        val usernameStream = RxTextView.textChanges(binding.inputUsername)
            .skipInitialValue()
            .map { username ->
                username.isEmpty()
            }
        usernameStream.subscribe {
            showUsernameMinimalAlert(it)
        }

        val passwordStream = RxTextView.textChanges(binding.inputPassword)
            .skipInitialValue()
            .map { password ->
                password.isEmpty()
            }
        passwordStream.subscribe {
            showPasswordMinimalAlert(it)
        }

        val invalidFieldsStream = Observable.combineLatest(
            usernameStream,
            passwordStream,
            { usernameInvalid: Boolean, passwordInvalid: Boolean ->
                !usernameInvalid && !passwordInvalid
            })
        invalidFieldsStream.subscribe { isValid ->
            binding.btnLogin.isEnabled = isValid
        }
    }

    private fun setLogin() {
        val username = binding.inputUsername.text.toString()
        val password = binding.inputPassword.text.toString()
        loginViewModel.getLogin(username, password).observe(this, { login ->
            if (login != null) {
                when (login) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.btnLogin.isEnabled = false
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnLogin.isEnabled = true
                        val dokter = Login(
                            login.data?.get(0)?.id,
                            login.data?.get(0)?.nama,
                            login.data?.get(0)?.username,
                            login.data?.get(0)?.password
                        )
                        userRepository.loginUser(dokter)
                        moveToMain()
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnLogin.isEnabled = true
                        Toast.makeText(
                            this,
                            login.message ?: resources.getString(R.string.something_wrong_message),
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

    private fun showUsernameMinimalAlert(isNotValid: Boolean) {
        binding.inputUsername.error = if (isNotValid) getString(R.string.username_not_valid) else null
    }

    private fun showPasswordMinimalAlert(isNotValid: Boolean) {
        binding.inputPassword.error = if (isNotValid) getString(R.string.password_not_valid) else null
    }
}