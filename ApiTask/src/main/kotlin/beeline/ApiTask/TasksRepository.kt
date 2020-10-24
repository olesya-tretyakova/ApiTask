package beeline.ApiTask

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TasksRepository:CrudRepository<Tasks,Long>
