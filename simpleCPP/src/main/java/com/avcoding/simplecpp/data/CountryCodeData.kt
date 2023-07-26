package com.avcoding.simplecpp.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Keep
@Parcelize
data class CountryCodeData(
    val code: String,
    val dial_code: String,
    val name: String
) : Parcelable