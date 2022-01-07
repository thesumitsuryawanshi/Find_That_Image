package com.example.flickagram.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flickagram.repository.repository

class ViewModelFactory(val repo: repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mainViewModel(repo) as T
    }
}