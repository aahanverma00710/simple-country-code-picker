package com.avcoding.simplecpp.utils

import androidx.recyclerview.widget.DiffUtil
import com.avcoding.simplecpp.data.CountryCodeData

class CountryCodeDiffUtils constructor(
    private val oldList: List<CountryCodeData>,
    private val newList: List<CountryCodeData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].dial_code == newList[newItemPosition].dial_code
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].toString() == newList[newItemPosition].toString()
    }
}