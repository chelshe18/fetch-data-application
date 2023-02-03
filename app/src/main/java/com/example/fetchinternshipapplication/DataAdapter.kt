package com.example.fetchinternshipapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchinternshipapplication.databinding.ActivityMainBinding
import com.example.fetchinternshipapplication.databinding.TableItemBinding
import org.w3c.dom.Text


class DataAdapter ()
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

    // To add the data to the RecyclerView Adapter
    fun setData(dataList: MutableList<DataItem>) {
        items = dataList
    }

    // Binds the data from the item list to the views
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    // Holds the views of the layout
    class DataViewHolder(val binding: TableItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemId: TextView = binding.itemId
        val itemListId: TextView = binding.itemListId
        val itemName: TextView = binding.itemName

        fun bind(dataItem: DataItem) {
            itemId.setText(dataItem.id)
            itemListId.setText(dataItem.listId)
            itemName.setText(dataItem.name)
        }
    }

}