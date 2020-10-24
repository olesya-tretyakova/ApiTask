package beeline.ApiTask

import java.util.*
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


enum class TasksStatus{
    New, InWork,Done
}


@Entity
class Tasks(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
var id:Long =0,
            var name:String,
            var description:String,
            var createDate:Date?,
            var urgent: Boolean,
            var status:TasksStatus?,
            var priority: Int?)
{
    constructor():this(name="",description = "",createDate = null,urgent = false,status = TasksStatus.New, priority = 0)
}