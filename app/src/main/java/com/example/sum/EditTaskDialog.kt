package com.example.sum

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

// Custom dialog for editing a task
class EditTaskDialog(
    context: Context,
    private val task: Task,
    private val taskList: MutableList<Task>,
    public val adapter: TaskAdapter,
    private val saveTasks: () -> Unit
) : Dialog(context) {

    // Called when the dialog is being created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for the dialog
        setContentView(R.layout.activity_dialog)

        // Find the EditText field for editing the task name
        val taskEditText: EditText = findViewById(R.id.taskEditText)
        // Set the initial text of the EditText to the current task name
        taskEditText.setText(task.name)

        // Find the update button in the dialog layout
        val updateButton: TextView = findViewById(R.id.updateButton)
        // Set click listener for the update button
        updateButton.setOnClickListener {
            // Update the task name with the text from the EditText
            task.name = taskEditText.text.toString()
            // Dismiss the dialog
            dismiss()
        }

        // Find the delete button in the dialog layout
        val deleteButton: TextView = findViewById(R.id.deleteButton)
        // Set click listener for the delete button
        deleteButton.setOnClickListener {
            // Remove the task from the list
            taskList.remove(task)
            // Notify the adapter that the dataset has changed
            adapter.notifyDataSetChanged()
            // Save the updated task list
            saveTasks()
            // Dismiss the dialog
            dismiss()
        }
    }
}
