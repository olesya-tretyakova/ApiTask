package beeline.ApiTask.resource
import beeline.ApiTask.model.Task
import beeline.ApiTask.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/tasks")
class TaskResource (private val taskService: TaskService){

//    @Autowired
//    lateinit var repository: TaskRepository

    @GetMapping
    fun getAllTasks() = taskService.getAllTasks()

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable(value="id") taskId:Long): ResponseEntity<Any> =taskService.getTaskById(taskId)

    @PostMapping
    fun addTask(@RequestBody task: Task?):ResponseEntity<Any> = taskService.addTask(task)

    @PutMapping("/{id}")
    fun putTaskById(@PathVariable(value="id") taskId: Long, @RequestBody task: Task?): ResponseEntity<Any> =
            taskService.putTaskById(taskId, task)

    @DeleteMapping("/{id}")
    fun deleteTaskById(@PathVariable(value="id") taskId:Long):ResponseEntity<Any> = taskService.deleteTaskById(taskId)


    fun notFoundResponse(taskId: Long) =
            ResponseEntity<Any>("Задача с Id = $taskId не найдена", HttpStatus.NOT_FOUND)


}