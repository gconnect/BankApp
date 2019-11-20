package com.glory.kinwaeui.utils

import android.content.Context
import android.os.Build
import com.glory.kinwaeui.dataModels.Banks

import com.glory.kinwaeui.dataModels.StateModel

import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.ArrayList

object AppUtil {

    fun StateFromJson(context: Context): ArrayList<StateModel> {
        // use this if the json file is a jsonObject
        val stateModelsList = ArrayList<StateModel>()
        val json = LoadJson(context)
        try {
            val jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                // get the current state object
                val jsonState = jsonArray.getJSONObject(i)
                val stateObject = jsonState.getJSONObject("state")
                val name = stateObject.getString("name")
                val localArray = stateObject.getJSONArray("locals")
                val cities = ArrayList<String>()

                for (j in 0 until localArray.length()) {
                    val city = localArray.getJSONObject(j).getString("name")
                    cities.add(city)
                }

                val stateModel = StateModel(name, cities)
                stateModelsList.add(stateModel)

            }
            return stateModelsList
            //            listView.setAdapter(new ArrayAdapter<>(MainActivity.this,
            //            android.R.layout.simple_list_item_1, contactList));

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return stateModelsList
    }


    fun LoadJson(context: Context): String? {
        var json: String? = null
        try {
            context.resources.assets
                .open("stateCity.json").use { inputStream ->
                    val size = inputStream.available()
                    val buffer = ByteArray(size)
                    inputStream.read(buffer)
                    inputStream.close()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        json = String(buffer, StandardCharsets.UTF_8)
                    }

                }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return json
    }



    fun BanksFromJson(context: Context): ArrayList<Banks> {
        val bankList = ArrayList<Banks>()

        try {
            val jsonDataString = LoadBankJson(context)
            val jsonArray = JSONArray(jsonDataString)

            for (i in 0 until jsonArray.length()) {
                // get the current state object

                // get the current plant object
                val jsonBanks = jsonArray.getJSONObject(i)
                val id = jsonBanks.getString("id")
                val name = jsonBanks.getString("name")
                val code = jsonBanks.getString("code")

                // create a new plant object
                val banks = Banks(name)

                // Add the contact to our contactList
                bankList.add(banks)
                }
            return bankList

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return bankList
    }


    fun LoadBankJson(context: Context): String? {
        var json: String? = null
        try {
            context.resources.assets
                .open("banks.json").use { inputStream ->
                    val size = inputStream.available()
                    val buffer = ByteArray(size)
                    inputStream.read(buffer)
                    inputStream.close()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        json = String(buffer, StandardCharsets.UTF_8)
                    }

                }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return json
    }

}
