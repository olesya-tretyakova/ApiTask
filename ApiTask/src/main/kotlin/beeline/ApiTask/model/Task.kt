package beeline.ApiTask.model

import java.util.*
import javax.persistence.*


enum class TaskStatus{
    New, InWork,Done
}


@Entity
@Table(name = "tasks")
class Task(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
            var id:Long =0,
            var name:String,
            var description:String,
            var createDate:Date?,
            var urgent: Boolean,
            var status: TaskStatus?,
            var priority: Int?)
{
    constructor():this(name="",description = "",createDate = null,urgent = false,status = TaskStatus.New, priority = 0)
}