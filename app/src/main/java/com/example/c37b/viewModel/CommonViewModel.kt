package com.example.c37b.viewModel

import android.content.Context
import android.net.Uri

import androidx.lifecycle.ViewModel
import com.example.c37b.repository.commonRepo

class CommonViewModel(val repo: commonRepo) : ViewModel()  {
    fun  uploadImage(context: Context, imageUri: Uri, callback: (Boolean, String?) -> Unit){
        repo.uploadImage(context,imageUri,callback)
    }
}