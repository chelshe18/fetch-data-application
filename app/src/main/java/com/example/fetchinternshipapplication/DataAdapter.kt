package com.example.fetchinternshipapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchinternshipapplication.databinding.TableItemBinding

class DataAdapter
    : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    private var items: MutableList<DataItem> = ArrayList()

    // Creates the ItemViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        return DataViewHolder(TableItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    // Returns number of items in list
    override fun getItemCount(): Int {
        return items.size
    }

    // Add the data to the RecyclerView Adapter
    fun setData(dataList: MutableList<DataItem>) {
        items = dataList
        sortData(dataList, 0, dataList.size)    // Sort the data list
    }

    // Sorts the DataItems using quicksort
    private fun sortData(list: MutableList<DataItem>, start: Int, end: Int) {
        if (end - start <= 1) {
            return
        }
        val partitionLocation = partition(list, start, end)
        sortData(list, start, partitionLocation)
        sortData(list, partitionLocation + 1, end)
    }

    // Partitioner for quicksort
    private fun partition(value: MutableList<DataItem>?, start: Int, end: Int): Int {
        if (value == null || value.size == 0) {
            return -1
        }
        var partitionIndex = start + 1
        val pivot = value[start]
        var temp: DataItem

        for (i in start + 1 until end) {
            if (value[i] < pivot) {
                temp = value[partitionIndex]
                value[partitionIndex] = value[i]
                value[i] = temp
                partitionIndex ++
            }
        }
        value[start] = value[partitionIndex - 1]
        value[partitionIndex - 1] = pivot

        return partitionIndex - 1
    }

    // Binds the data from the item list to the views
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // Holds the views of the layout
    class DataViewHolder(private val binding: TableItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val itemId: TextView = binding.itemId
        private val itemListId: TextView = binding.itemListId
        private val itemName: TextView = binding.itemName

        // Binds the DataItem attributes to the views
        fun bind(dataItem: DataItem) {
            itemId.text = dataItem.id.toString()
            itemListId.text = dataItem.listId.toString()
            itemName.text = dataItem.name
        }
    }

}