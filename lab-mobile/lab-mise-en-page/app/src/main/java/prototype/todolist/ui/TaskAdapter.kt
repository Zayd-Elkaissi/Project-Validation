package prototype.todolist.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import prototype.todolist.R
import prototype.todolist.data.TaskRepository
import java.text.SimpleDateFormat
import java.util.Date


class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    private val taskRepository = TaskRepository()
    class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val taskTitle: TextView = view.findViewById<Button>(R.id.taskTitle)
        val taskPriority: TextView = view.findViewById<Button>(R.id.taskPriority)
        val prioritiesArray = view.resources.getStringArray(R.array.priorities)
        val taskTimestamp: TextView = view.findViewById<Button>(R.id.taskTimestamp)
        val cardView: CardView = view.findViewById(R.id.cardview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return taskRepository.getAllTasks().size
    }

    override fun onBindViewHolder(taskViewHolder: TaskViewHolder, position: Int) {
        val task = this.taskRepository.getAllTasks()[position]
        val priority = taskViewHolder.taskPriority
        val prioritiesArray = taskViewHolder.prioritiesArray
        taskViewHolder.taskTitle.text = task.title
        if(task.priority.equals(1)){
            priority.text = prioritiesArray[0]
        }else if(task.priority.equals(2)){
            priority.text = prioritiesArray[1]
        }else if(task.priority.equals(3)){
            priority.text = prioritiesArray[2]
        }

        // Format the date and set it to the taskTimestamp
        val date = Date()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val formattedDate = dateFormat.format(date)
        taskViewHolder.taskTimestamp.text = formattedDate

        taskViewHolder.cardView.setOnClickListener {

            task.title = task.title + "+"
            // Todo : supprimer ces deux lignes et voir est ce que RecyclerView continue d'afficher les updates ?
            val repository = TaskRepository()
            repository.save(task)
            this.notifyDataSetChanged()

            // Todo : Afficher un message aprés Update
            // Toast.makeText(context,"Update $task", Toast.LENGTH_LONG).show()
        }
    }

}