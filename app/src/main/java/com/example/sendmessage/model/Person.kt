package com.example.sendmessage.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person (val dni: String, val name: String, val surname: String) : Parcelable{

}