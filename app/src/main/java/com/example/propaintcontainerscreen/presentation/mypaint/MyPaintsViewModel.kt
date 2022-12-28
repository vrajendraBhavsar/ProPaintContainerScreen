package com.example.propaintcontainerscreen.presentation.mypaint

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propaintcontainerscreen.data.PaintContainer
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.IOException

class MyPaintsViewModel: ViewModel() {

    /*private val _containerAmount = MutableLiveData<Int>(0)
    val containerAmount : LiveData<Int> = _containerAmount

    private val _itemQuantity = MutableLiveData<Int>(1)
    val itemQuantity : LiveData<Int> = _itemQuantity*/

    fun getContainerData(context: Context): List<PaintContainer> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("container.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("TAG", "!@# getCountryCode: $ioException")
        }
        val listContainerType = object : TypeToken<List<PaintContainer>>() {}.type
        /*val t: List<PaintContainer> = Gson().fromJson(jsonString, listContainerType)
        Log.d("TAG", "!@# getContainerData: jsonString => $t")*/
        return Gson().fromJson(jsonString, listContainerType)
    }
//here Gson() is basically providing fromJson methods which actually
//deserializing json. It basically parse json string to
// list<Country> object
//this getCountryCode(ctx: Context) will return a list of Country data class.
}