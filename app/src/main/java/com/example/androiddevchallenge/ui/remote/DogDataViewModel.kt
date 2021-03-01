package com.example.androiddevchallenge.ui.remote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.entity.DogEntity

class DogDataViewModel():ViewModel() {
    var myData: MutableLiveData<MutableList<DogEntity>> = MutableLiveData()
    fun refreshDogData(){
        var dogList= mutableListOf<DogEntity>()
        for (i in 0 until 20){
            dogList.add(DogEntity(name = "hhh$i", R.drawable.ic_launcher_foreground,desc = "desc$i"))
        }
        myData.value =dogList
    }
}