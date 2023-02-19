package com.example.idkkkk.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idkkkk.model.Contact
import com.example.idkkkk.model.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsListViewMoedel @Inject constructor(
    private val db: ContactDatabase
) : ViewModel() {

    var contacts by mutableStateOf(emptyList<Contact>())
    private set
    init {
        getContacts()
    }

    fun getContacts(){
        db.dao.getContact().onEach {contactList ->
                contacts = contactList
        }.launchIn(viewModelScope)
    }

    fun removeContact(contact: Contact){
        viewModelScope.launch {

            db.dao.removeContact(
               contact
            )
        }
    }


}