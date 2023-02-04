package com.example.fetchinternshipapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchinternshipapplication.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    // Will be initialized in onCreate
    private lateinit var dataAdapter: DataAdapter       // Adapter for RecyclerView
    private lateinit var binding:ActivityMainBinding    // For binding to views

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
        dataAdapter.setData(retrieveData())
    }

    // Retrieves the data from Json file
    fun retrieveData(): MutableList<DataItem> {
        val output = mutableListOf<DataItem>()  // List to output
        var newDataItem: DataItem               // DataItem to add to output
        val gson = Gson()

        // Read Json file to a List of Strings
        val jsonString: String = application.assets.open("fetchdataset.json")
            .bufferedReader()
            .use { it.readText() }

        // Create a mutable list of lines of the Json string
        var lines = jsonString.split("\n")
        lines = lines.toMutableList()
        lines.remove("[")
        lines.remove("]")

        // Deserialize to Kotlin class DataItem and add to output
        for (line in lines) {
            val trimmedLine = line.trim().dropLast(1)   // Remove the comma
            try {
                newDataItem = gson.fromJson(trimmedLine, DataItem::class.java)
                if (newDataItem.name != null && newDataItem.name != "") {
                    output.add(newDataItem)
                }
            } catch(_: Exception) { }
        }

        return output
    }
}