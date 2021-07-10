package com.example.fooddeliveryaggregator.search.view

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.annotation.LayoutRes

class NoFilterArrayAdapter<T>(
    context: Context,
    @LayoutRes textViewResourceId: Int,
    objects: List<T>
) : ArrayAdapter<T>(context, textViewResourceId, objects) {
    private val filter: Filter = NoFilter()
    private var items: List<T> = objects
    override fun getFilter(): Filter {
        return filter
    }

    private inner class NoFilter : Filter() {
        override fun performFiltering(arg0: CharSequence?): FilterResults {
            val result = FilterResults()
            result.values = items
            result.count = items.size
            return result
        }

        override fun publishResults(arg0: CharSequence?, arg1: FilterResults) {
            notifyDataSetChanged()
        }
    }

}