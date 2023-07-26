package com.avcoding.simplecountrycodepicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.avcoding.simplecountrycodepicker.databinding.ActivityMainBinding
import com.avcoding.simplecpp.data.CountryCodeData
import com.avcoding.simplecpp.ui.SimpleCountryCodePickerActivity
import com.avcoding.simplecpp.utils.parcelable

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViews()
        setUpClick()
    }


    private fun setUpViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpClick() {
        binding.tvFetchCode.setOnClickListener(this@MainActivity)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.tvFetchCode -> {
                val intent = Intent(this@MainActivity, SimpleCountryCodePickerActivity::class.java)
                countryCodePicker.launch(intent)
            }
        }
    }


    private val countryCodePicker =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    val countryCodeData =
                        result.data?.extras?.parcelable(SimpleCountryCodePickerActivity.COUNTRY_CODE_ARGS) as CountryCodeData?
                    countryCodeData?.let {
                        binding.tvFetchCode.text = "${it.name}\n${it.dial_code}\n${it.code}"
                    }
                }
            }
        }

}