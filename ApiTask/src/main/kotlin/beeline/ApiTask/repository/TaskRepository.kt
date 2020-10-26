package beeline.ApiTask.repository

import beeline.ApiTask.model.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : CrudRepository<Task,Long>
