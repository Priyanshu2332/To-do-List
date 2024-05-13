package com.example.sum

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

// Custom adapter for displaying tasks in a ListView
class TaskAdapter(private val context: Context, private val tasks: MutableList<Task>) : BaseAdapter() {

    // Return the number of tasks in the list
    override fun getCount(): Int {
        return tasks.size
    }

    // Return the task at the specified position
    override fun getItem(position: Int): Task {
        return tasks[position]
    }

    // Return the ID of the item at the specified position
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Create and return a View for each item in the list
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Get the task at the specified position
        val task = getItem(position)
        // Inflate the layout for each list item (if necessary)
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_edit_task, parent, false)
        // Set the task name to the TextView in the list item layout
        view.findViewById<TextView>(R.id.taskNameTextView).text = task.name
        return view
    }
}
