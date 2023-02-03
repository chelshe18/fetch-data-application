package com.example.fetchinternshipapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchinternshipapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Will be initialized in onCreate
    private lateinit var dataAdapter: DataAdapter
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: ConstraintLayout = binding.root
        setContentView(view)

        initDataTableView() // Initialize the data table view
        addDataList()       // Add the list of data to the adapter
    }

    private fun initDataTableView() {
        binding.rvDataTable.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            dataAdapter = DataAdapter()
            adapter = dataAdapter
        }
    }

    // Add list of data to the RecyclerView
    private fun addDataList() {
        var item1 = DataItem("12", "1", "One")
        var item2 = DataItem("43", "2", "Two")
        var item3 = DataItem("34", "3", "Three")
        var data = mutableListOf<DataItem>(item1, item2, item3)
        dataAdapter.setData(data)
    }
}