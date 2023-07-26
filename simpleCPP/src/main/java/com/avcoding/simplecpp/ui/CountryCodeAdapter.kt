package com.avcoding.simplecpp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.avcoding.simplecpp.data.CountryCodeData
import com.avcoding.simplecpp.databinding.AdapterCountryCodeBinding
import com.avcoding.simplecpp.utils.CountryCodeDiffUtils

class CountryCodeAdapter constructor(val callBack: (CountryCodeData) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var oldList = arrayListOf<CountryCodeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = AdapterCountryCodeBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder){
            holder.bind(oldList[position])
        }
    }

    fun setData(newList: List<CountryCodeData>){
        val diffResult = DiffUtil.calculateDiff(
            CountryCodeDiffUtils(oldList, newList),
            true
        )
        oldList = ArrayList(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ItemViewHolder(private val binding: AdapterCountryCodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val data = oldList[adapterPosition]
                callBack.invoke(data)
            }
        }
        fun bind(data: CountryCodeData) {

            binding.tvCountryCode.text = data.dial_code
            binding.tvCountryName.text = data.name
            binding.tvCountryAbber.text = data.code
        }

    }
}