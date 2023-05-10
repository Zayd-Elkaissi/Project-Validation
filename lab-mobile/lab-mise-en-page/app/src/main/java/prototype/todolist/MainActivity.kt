package prototype.todolist


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import prototype.todolist.data.TaskRepository
import prototype.todolist.databinding.ActivityMainBinding
import prototype.todolist.ui.TaskAdapter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            val taskAdapter = TaskAdapter()
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.adapter =  taskAdapter
            floatingActionButton.setOnClickListener{

                val repository = TaskRepository()
                val newTask = repository.newTask();
                newTask.title = "New task" + Random.nextInt()
                newTask.priority = 3
                repository.save(newTask)

                Toast.makeText(applicationContext,"Ajouter une tâche", Toast.LENGTH_LONG).show()
                taskAdapter.notifyDataSetChanged()
            }

        }

    }

}