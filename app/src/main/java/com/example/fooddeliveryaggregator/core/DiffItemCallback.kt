package com.example.fooddeliveryaggregator.core

import androidx.recyclerview.widget.DiffUtil

open class DiffItemCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return newItem == oldItem
    }
}