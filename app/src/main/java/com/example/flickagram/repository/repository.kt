package com.example.flickagram.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flickagram.Model.DataModel
import com.example.flickagram.Model.ImageApi
import retrofit2.await

class repository(val ImageApi: ImageApi) {


    private val mutableLiveData = MutableLiveData<DataModel>()


    val ImagesList: LiveData<DataModel>
        get() = mutableLiveData

    suspend fun getImages(searchKeyword: String) {
        val data = ImageApi.getImagesList(searchKeyword).await()
        mutableLiveData.postValue(data)
    }

}