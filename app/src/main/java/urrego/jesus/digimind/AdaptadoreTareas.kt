package urrego.jesus.digimind

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import urrego.jesus.digimind.ui.Task

class AdaptadoreTareas: BaseAdapter {
    lateinit var  context: Context
    var tasks: ArrayList<Task> = ArrayList<Task>()

    constructor(context: Context, tasks: ArrayList<Task>) {
        this.context = context
        this.tasks = tasks
    }

    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(position: Int): Any {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.task_view, null)

        var task = tasks[position]

        val tv_title: TextView = view.findViewById(R.id.tv_title)
        val tv_time: TextView = view.findViewById(R.id.tv_time)
        val tv_day: TextView = view.findViewById(R.id.tv_days)

        tv_title.setText(task.title)
        tv_time.setText(task.time)
        tv_day.setText(task.day)

        return view
    }
}