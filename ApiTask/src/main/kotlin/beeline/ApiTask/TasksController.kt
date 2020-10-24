package beeline.ApiTask

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/tasks")
class TasksController {

    @Autowired
    lateinit var repository: TasksRepository

    @GetMapping
    fun getAllTasks() = repository.findAll()

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id:Long): ResponseEntity<Any>{
        val entity = repository.findById(id)
        if (entity.isEmpty) return notFoundResponse(id)
        return ResponseEntity(entity, HttpStatus.OK)
    }

    @PostMapping
    fun addTask(@RequestBody task:Tasks?):ResponseEntity<Any>{
        if (task == null) return ResponseEntity("Нет данных в запросе", HttpStatus.BAD_REQUEST)
        val saved = repository.save(task)
        return ResponseEntity("Задача сохранена под номером Id=${saved.id}", HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun putTaskById(@PathVariable id:Long, @RequestBody task: Tasks?): ResponseEntity<Any>{
        return when {
            task == null -> ResponseEntity("Нет данных в запросе", HttpStatus.BAD_REQUEST)
            repository.findById(id).isEmpty -> notFoundResponse(id)
            else -> {
                task.id = id
                repository.save(task)
                ResponseEntity("Задача с Id = $id изменена", HttpStatus.OK)
            }
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTaskById(@PathVariable id:Long):ResponseEntity<Any>{
        val entity = repository.findById(id)
        if (entity.isEmpty) return notFoundResponse(id)
        repository.deleteById(id)
        return ResponseEntity("Задача с Id =$id удалена", HttpStatus.OK)
    }


    fun notFoundResponse(id: Long) =
            ResponseEntity<Any>("Задача с Id = $id не найдена", HttpStatus.NOT_FOUND)


}