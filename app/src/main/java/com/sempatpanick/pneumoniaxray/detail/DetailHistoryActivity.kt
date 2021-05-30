package com.sempatpanick.pneumoniaxray.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sempatpanick.pneumoniaxray.R
import com.sempatpanick.pneumoniaxray.core.domain.model.History
import com.sempatpanick.pneumoniaxray.databinding.ActivityDetailHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail = intent.getParcelableExtra<History>(EXTRA_DATA)
        if (detail != null) {
            setUp(detail)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUp(detail: History) {
        supportActionBar?.title = resources.getString(R.string.patient_detail)
        detail.let {
            Glide.with(this@DetailHistoryActivity)
                .load(it.urlGambar)
                .into(binding.imgXray)
            binding.tvPrediction.text = it.prediction
            binding.tvIdPatient.text = it.idPasien
            binding.tvPatientName.text = it.namaPasien
            binding.tvPatientBirthday.text = it.tanggalLahirPasien
            binding.tvPatientAge.text = "-"
            binding.tvPatientAddress.text = it.alamatPasien
            binding.tvPatientPhoneNumber.text = it.telpPasien
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}