package urrego.jesus.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import urrego.jesus.digimind.AdaptadorTareas
import urrego.jesus.digimind.databinding.FragmentHomeBinding
import urrego.jesus.digimind.ui.Task

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    companion object{
        var tasks: ArrayList<Task> = ArrayList<Task>()
        var first = true
        lateinit var adapter: AdaptadorTareas
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val gridView: GridView = binding.gridview

//        if(first) {
//            fill_tasks()
//            first = false
//        }

        cargar_tareas()

        adapter = AdaptadorTareas(root.context, tasks)

        gridView.adapter = adapter

        return root
    }

    fun fill_tasks() {

    }

    fun cargar_tareas() {
        val preferencias = context?.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val gson: Gson = Gson()
        var json = preferencias?.getString("tareas", null)

        val type = object: TypeToken<ArrayList<Task?>?>() {}.type

        if(json == null) {
            tasks = ArrayList<Task>()
        } else {
            tasks = gson.fromJson(json, type)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}