package com.avcoding.simplecpp.utils

import android.content.Context
import com.avcoding.simplecpp.data.CountryCodeData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FetchCountryCode constructor(private val context:Context) {

    private  lateinit var jsonString: String

    private var countryCodeList = ArrayList<CountryCodeData>()
    init {
        fetchCode()
    }

    private fun fetchCode() {
        try {
            jsonString = context.assets.open("country_code.json")
                .bufferedReader()
                .use { it.readText() }
            updateCountryCode()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun updateCountryCode() {
        val listCountryType = object : TypeToken<List<CountryCodeData>>() {}.type
        countryCodeList =  Gson().fromJson(jsonString, listCountryType)
    }

    fun getCountryCodeList() : List<CountryCodeData>{
        return countryCodeList
    }

    fun searchInList(keyword: String): List<CountryCodeData>{
        val filteredList = countryCodeList.filter {
            it.dial_code.contains(keyword,true) ||
                    it.name.contains(keyword,true) ||
                    it.code.contains(keyword,true)
        }
        return filteredList
    }
}