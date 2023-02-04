package com.example.fetchinternshipapplication

data class DataItem(
        val id: Int,
        val listId: Int,
        val name: String?
        ) : Comparable<DataItem> {

        override operator fun compareTo(other: DataItem) = when (listId) {
            other.listId -> name!!.drop(5).toInt() - other.name!!.drop(5).toInt()
            else -> listId - other.listId
        }

        }