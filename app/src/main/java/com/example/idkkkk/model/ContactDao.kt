package com.example.idkkkk.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert
    suspend fun addContact(contact: Contact)

    @Delete
    suspend fun removeContact(contact: Contact)



    @Query("select * from Contact")
     fun getContact() :Flow<List<Contact>>
}