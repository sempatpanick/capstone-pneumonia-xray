package com.alvex.pneumoniaxray.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alvex.pneumoniaxray.R
import com.alvex.pneumoniaxray.core.domain.model.History
import com.alvex.pneumoniaxray.databinding.ActivityDetailHistoryBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailHistoryActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

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
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading_image))
                .error(R.drawable.ic_error_image)
                .into(binding.imgXray)
            binding.tvPrediction.text = it.prediction.replaceFirstChar { char ->
                char.uppercaseChar()
            }
            binding.tvIdPatient.text = it.idPasien
            binding.tvPatientName.text = it.namaPasien
            binding.tvPatientBirthday.text = it.tanggalLahirPasien
            binding.tvPatientAge.text = getAge(it.tanggalLahirPasien)
            binding.tvPatientAddress.text = it.alamatPasien
            binding.tvPatientPhoneNumber.text = it.telpPasien
        }

    }

    private fun getAge(date: String): CharSequence {
        val calendar = Calendar.getInstance()
        val today = Calendar.getInstance()
        val parseYears = date.subSequence(0, 4).toString()
        val parseMonth = date.subSequence(5, 7).toString()
        val parseDay = date.subSequence(8, 10).toString()

        val years = parseYears.toInt()
        val month = parseMonth.toInt()
        val day = parseDay.toInt()

        val birth = Calendar.getInstance()
        birth.set(years, month, day)

        var age = calendar.get(Calendar.YEAR) - birth.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age.toString()
    }
}