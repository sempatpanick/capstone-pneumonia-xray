package com.alvex.pneumoniaxray.core.data

import com.alvex.pneumoniaxray.core.domain.model.Login
import com.alvex.pneumoniaxray.core.manager.SessionManager
import javax.inject.Inject

class UserRepository @Inject constructor(private val sesi: SessionManager) {

    fun loginUser(login: Login) {
        sesi.createLoginSession()
        login.id?.let { sesi.saveToPreference(SessionManager.KEY_ID, it) }
        login.nama?.let { sesi.saveToPreference(SessionManager.KEY_NAMA, it) }
        login.username?.let { sesi.saveToPreference(SessionManager.KEY_USERNAME, it) }
        login.password?.let { sesi.saveToPreference(SessionManager.KEY_PASSWORD, it) }
    }

    fun getUser(): Login {
        return Login(
            sesi.getFromPreference(SessionManager.KEY_ID),
            sesi.getFromPreference(SessionManager.KEY_NAMA),
            sesi.getFromPreference(SessionManager.KEY_USERNAME),
            sesi.getFromPreference(SessionManager.KEY_PASSWORD),
        )
    }

    fun isUserLogin() = sesi.isLogin

    fun logoutUser() = sesi.logout()

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(sesi: SessionManager): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(sesi)
            }
    }
}