package com.sempatpanick.pneumoniaxray.core.data

import com.sempatpanick.pneumoniaxray.core.domain.model.Doctor
import com.sempatpanick.pneumoniaxray.core.manager.SessionManager
import javax.inject.Inject

class UserRepository @Inject constructor(private val sesi: SessionManager) {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(sesi: SessionManager): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(sesi)
            }
    }

    fun loginUser(dokter: Doctor) {
        sesi.createLoginSession()
        dokter.idDoctor?.let { sesi.saveToPreference(SessionManager.KEY_IDDOCTOR, it) }
        dokter.nama?.let { sesi.saveToPreference(SessionManager.KEY_NAMA, it) }
        dokter.username?.let { sesi.saveToPreference(SessionManager.KEY_USERNAME, it) }
        dokter.password?.let { sesi.saveToPreference(SessionManager.KEY_PASSWORD, it) }
    }

    fun getUser(): Doctor {
        return Doctor(
            sesi.getFromPreference(SessionManager.KEY_IDDOCTOR),
            sesi.getFromPreference(SessionManager.KEY_NAMA),
            sesi.getFromPreference(SessionManager.KEY_USERNAME),
            sesi.getFromPreference(SessionManager.KEY_PASSWORD),
        )
    }

    fun isUserLogin() = sesi.isLogin

    fun logoutUser() = sesi.logout()
}