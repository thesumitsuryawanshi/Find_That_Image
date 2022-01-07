package com.example.flickagram.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickagram.Model.DataModel
import com.example.flickagram.repository.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class mainViewModel(val repository: repository) : ViewModel() {


    fun getIamgesList(searchKeyword:String) {
        viewModelScope.launch(Dispatchers.IO) {
             repository.getImages(searchKeyword)
        }
    }

    val ImagesList: LiveData<DataModel>
        get() = repository.ImagesList
}