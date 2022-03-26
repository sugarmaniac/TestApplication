package com.sugarmaniac.testapplication.model

import android.os.Parcelable
import com.sugarmaniac.testapplication.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Device(
    val Firmware: String,
    val InternalIP: String,
    val LastAliveReported: String,
    val MacAddress: String,
    val PK_Account: Int,
    val PK_Device: Int,
    val PK_DeviceSubType: Int,
    val PK_DeviceType: Int,
    val Platform: String,
    val Server_Account: String,
    val Server_Device: String,
    val Server_Event: String,
    var Device_Name : String? = null
): Parcelable {
    fun getTitle() : String{
        return if (Device_Name.isNullOrBlank()){
            "HOME NUMBER"
        } else {
            Device_Name!!
        }
    }

    fun getImageId() : Int {
        return when (Platform){
            "Sercomm G450" -> R.drawable.vera_plus_big
            "Sercomm G550" -> R.drawable.vera_secure_big
            else -> R.drawable.vera_edge_big
        }
    }
}