package com.glory.kinwaeui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.glory.kinwaeui.adapter.BankAdapter
import com.glory.kinwaeui.adapter.SpinnerCityAdapter
import com.glory.kinwaeui.dataModels.Banks
import com.glory.kinwaeui.dataModels.StateModel
import com.glory.kinwaeui.databinding.ActivityMainBinding
import com.glory.kinwaeui.utils.AppUtil

class MainActivity : AppCompatActivity(){

    lateinit var binding : ActivityMainBinding
    lateinit var state : String
    lateinit var bankStr : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        stateCitySpinner()
        BankSpinner()
    }


    fun stateCitySpinner() {
        val stateCityList = AppUtil.StateFromJson(this)

        binding.stateSpinner.setAdapter(
            SpinnerCityAdapter(
                this@MainActivity,
                android.R.layout.simple_dropdown_item_1line, R.id.SpinnertextView, stateCityList
            )
        )
        binding.stateSpinner?.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val stateModel = parent?.getItemAtPosition(position) as StateModel
                state = stateModel.name
                var adapter: ArrayAdapter<String>? = null
                if (adapter != null) {
                    adapter.clear()
                    adapter.notifyDataSetChanged()
                }

                adapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1, stateModel.locals
                )
                binding.citySpinner.setAdapter(adapter)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

    }


    fun BankSpinner() {
        val bankList = AppUtil.BanksFromJson(this)

        binding.branchSpinner.setAdapter(
            BankAdapter(
                this@MainActivity,
                android.R.layout.simple_dropdown_item_1line, R.id.SpinnertextView, bankList
            )
        )
        binding.branchSpinner?.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val bank = parent?.getItemAtPosition(position) as Banks
                bankStr = bank.name
                var adapter: ArrayAdapter<String>? = null
                if (adapter != null) {
                    adapter.clear()
                    adapter.notifyDataSetChanged()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

    }

}
