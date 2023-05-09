package prototype.todolist

import prototype.todolist.data.TaskDao
import prototype.todolist.data.TaskEntry
import prototype.todolist.data.TaskRepository

fun main (){

     val taskRepository = TaskRepository()
    // AjZouter une tâche
    val task = TaskEntry(1,"Task 1",1,System.currentTimeMillis())
//    val task1 = TaskEntry(1,"Task 1",1,System.currentTimeMillis())
     taskRepository.save(task);
   // println(task.title)
//    println(task == task1)

    // Suprimer une tâche
    //taskRepository.delete(4)

    // Modifier une tâche
    val updateTask = taskRepository.findById(1)
    updateTask.title = "task 100"

    // Afficher les tâches
    for(item in taskRepository.getAllTasks()){
        println(item.title)
    }


}