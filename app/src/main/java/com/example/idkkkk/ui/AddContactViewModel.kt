package com.example.idkkkk.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idkkkk.model.Contact
import com.example.idkkkk.model.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val db: ContactDatabase
        ): ViewModel() {

            fun addContact(name: String, phone: String){
                viewModelScope.launch {
                var contact = Contact(name = name, phone = phone)
                    db.dao.addContact(
                        contact
                    )
                }
            }
}