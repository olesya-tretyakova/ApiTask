package beeline.ApiTask.service

import beeline.ApiTask.model.Task
import beeline.ApiTask.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class TaskService(private val repository: TaskRepository) {

    fun getAllTasks() = repository.findAll()

    fun getTaskById(taskId:Long): ResponseEntity<Any> {
        val entity = repository.findById(taskId)
        if (entity.isEmpty) return notFoundResponse(taskId)
        return ResponseEntity(entity, HttpStatus.OK)
    }

    fun addTask(@RequestBody task: Task?): ResponseEntity<Any> {
        if (task == null) return ResponseEntity("Нет данных в запросе", HttpStatus.BAD_REQUEST)
        val saved = repository.save(task)
        return ResponseEntity("Задача сохранена под номером Id=${saved.id}", HttpStatus.CREATED)
    }

   fun putTaskById(taskId: Long, @RequestBody task: Task?): ResponseEntity<Any> {
        return when {
            task == null -> ResponseEntity("Нет данных в запросе", HttpStatus.BAD_REQUEST)
            repository.findById(taskId).isEmpty -> notFoundResponse(taskId)
            else -> {
                task.id = taskId
                repository.save(task)
                ResponseEntity("Задача с Id = $taskId изменена", HttpStatus.OK)
            }
        }
    }

    fun deleteTaskById(taskId: Long): ResponseEntity<Any> {
        val entity = repository.findById(taskId)
        if (entity.isEmpty) return notFoundResponse(taskId)
        repository.deleteById(taskId)
        return ResponseEntity("Задача с Id =$taskId удалена", HttpStatus.OK)
    }


    fun notFoundResponse(id: Long) =
            ResponseEntity<Any>("Задача с Id = $id не найдена", HttpStatus.NOT_FOUND)

}