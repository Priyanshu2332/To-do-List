package com.example.sum

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var taskList: MutableList<Task> // Declare a mutable list to store tasks
    private lateinit var adapter: TaskAdapter // Declare an adapter for the ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the layout for the activity

        // Load tasks from SharedPreferences or create an empty list
        taskList = loadTasks()

        // Initialize the ListView and set its adapter
        val listView: ListView = findViewById(R.id.listView)
        adapter = TaskAdapter(this, taskList)
        listView.adapter = adapter

        // Initialize the 'Add' button and EditText for adding tasks
        val addButton: TextView = findViewById(R.id.addButton)
        val taskEditText: EditText = findViewById(R.id.taskEditText)

        // Add a click listener to the 'Add' button
        addButton.setOnClickListener {
            val taskName = taskEditText.text.toString() // Get the task name from the EditText
            if (taskName.isNotEmpty()) { // Check if the task name is not empty
                val task = Task(taskList.size + 1, taskName) // Create a new Task instance
                taskList.add(task) // Add the task to the list
                adapter.notifyDataSetChanged() // Notify the adapter that the dataset has changed
                saveTasks() // Save the updated task list
                taskEditText.text.clear() // Clear the EditText
            }
        }

        // Set an item click listener for the ListView
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val task = taskList[position] // Get the task at the clicked position

            // Create and show the edit task dialog
            val dialog = EditTaskDialog(this, task, taskList, this@MainActivity.adapter, ::saveTasks)
            dialog.show()
        }
    }

    // Function to load tasks from SharedPreferences
    private fun loadTasks(): MutableList<Task> {
        val sharedPreferences: SharedPreferences = getSharedPreferences("tasks", Context.MODE_PRIVATE)
        val json: String? = sharedPreferences.getString("taskList", null)
        val type = object : TypeToken<MutableList<Task>>() {}.type
        return Gson().fromJson(json, type) ?: mutableListOf() // Return the deserialized list or an empty list
    }

    // Function to save tasks to SharedPreferences
    private fun saveTasks() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("tasks", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(taskList) // Convert the task list to JSON string
        editor.putString("taskList", json) // Store the JSON string in SharedPreferences
        editor.apply() // Apply the changes
    }
}
