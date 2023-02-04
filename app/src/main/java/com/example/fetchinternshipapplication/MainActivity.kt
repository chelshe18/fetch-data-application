package com.example.fetchinternshipapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchinternshipapplication.databinding.ActivityMainBinding
import com.google.gson.Gson

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
        dataAdapter.setData(retrieveData())
    }

    // Retrieves the data from Json file
    fun retrieveData(): MutableList<DataItem> {
        var jsonString: String
        var output = mutableListOf<DataItem>()  // List to output
        var newDataItem: DataItem               // DataItem to add to output
        val gson = Gson()

        // Read Json file to a List of Strings
        jsonString = application.assets.open("fetchdataset.json")
            .bufferedReader()
            .use { it.readText() }

        var lines = jsonString.split("\n")

        var newLines = lines.toMutableList()
        newLines.remove("[")
        newLines.remove("]")

        // Translate to Kotlin class DataItem and add to output
        for (line in newLines) {
            var trimmedLine = line.trim().dropLast(1)
            try {
                newDataItem = gson.fromJson(trimmedLine, DataItem::class.java)
                if (newDataItem.name != null && newDataItem.name != "") {
                    output.add(newDataItem)
                }
            } catch(e: Exception) { }
        }

        return output
    }
}