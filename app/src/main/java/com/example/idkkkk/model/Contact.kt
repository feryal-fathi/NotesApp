package com.example.idkkkk.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var phone : String? = null

) : Parcelable
