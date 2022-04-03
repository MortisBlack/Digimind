package urrego.jesus.digimind

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import urrego.jesus.digimind.ui.Task
import urrego.jesus.digimind.ui.home.HomeFragment

class AdaptadorTareas: BaseAdapter {
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

        view.setOnClickListener{
            eliminate(task)
        }

        return view
    }

    fun eliminate(task: Task) {
        var alertDialog: AlertDialog? = context?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok_button, { dialog, id ->
                            HomeFragment.tasks.remove(task)
                            HomeFragment.adapter.notifyDataSetChanged()
                            guardar_json()
                            Toast.makeText(context, R.string.msg_deleted, Toast.LENGTH_SHORT).show()
                    })
                setNegativeButton(R.string.cancel_button, { dialog, id ->

                })
            }
            builder?.setMessage(R.string.msg).setTitle(R.string.title)

            builder.create()
        }

        alertDialog?.show()
    }

    fun guardar_json() {
        val preferencias = context?.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val editor = preferencias?.edit()

        val gson: Gson = Gson()

        var json = gson.toJson(HomeFragment.tasks)

        editor?.putString("tareas", json)

        editor?.apply()
    }
}