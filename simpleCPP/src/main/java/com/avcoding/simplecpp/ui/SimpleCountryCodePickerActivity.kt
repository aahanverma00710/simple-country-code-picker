package com.avcoding.simplecpp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.avcoding.simplecpp.databinding.ActivitySimpleCountryCodePickerBinding
import com.avcoding.simplecpp.utils.FetchCountryCode

class SimpleCountryCodePickerActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivitySimpleCountryCodePickerBinding

    lateinit var adapter: CountryCodeAdapter

    private val countryCodeData: FetchCountryCode by lazy {
        FetchCountryCode(this@SimpleCountryCodePickerActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViews()
        setUpClicks()
        setUpAdapter()
        setUpTextWatcher()
        updateAdapter()
    }

    private fun updateAdapter() {
        if (::adapter.isInitialized) {
            val list = countryCodeData.getCountryCodeList()
            adapter.setData(list)
        }
    }


    private fun setUpViews() {
        binding = ActivitySimpleCountryCodePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpClicks() {
        binding.ivBack.setOnClickListener(this@SimpleCountryCodePickerActivity)
    }

    private fun setUpAdapter() {
        adapter = CountryCodeAdapter {
            val intent = Intent()
            val bundle = bundleOf(COUNTRY_CODE_ARGS to it)
            intent.putExtras(bundle)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        binding.rvCountryCode.adapter = adapter
    }


    private fun setUpTextWatcher() {
        binding.etSearch.addTextChangedListener {
            handleSearch(it.toString().trim())
        }
    }

    private fun handleSearch(searchQuery: String) {
        val filteredList = countryCodeData.searchInList(searchQuery)
        if (filteredList.isEmpty()){
            binding.rvCountryCode.isVisible =false
            binding.tvNoDataFound.isVisible =true
            adapter.setData(arrayListOf())
        }else{
            binding.rvCountryCode.isVisible =true
            binding.tvNoDataFound.isVisible =false
            adapter.setData(filteredList)
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.ivBack -> {
                finish()
            }
        }
    }

    companion object{
        const val COUNTRY_CODE_ARGS = "country-code-args"
    }

}