package com.example.tabitaparadizzomfusion.ui.home
/*
Tabita - 19551
Marcelle - 19534
*/

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to MFusion"
    }
    val text: LiveData<String> = _text
}