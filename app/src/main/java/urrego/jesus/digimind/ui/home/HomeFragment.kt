package urrego.jesus.digimind.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import urrego.jesus.digimind.AdaptadoreTareas
import urrego.jesus.digimind.R
import urrego.jesus.digimind.databinding.FragmentHomeBinding
import urrego.jesus.digimind.ui.Task

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    var tasks: ArrayList<Task> = ArrayList<Task>()

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

        fill_tasks()
        
        val adapter = AdaptadoreTareas(root.context, tasks)

        gridView.adapter = adapter

        return root
    }

    fun fill_tasks() {
        tasks.add(Task("Tarea 1", "Lunes", "15:00"))
        tasks.add(Task("Tarea 2", "Lunes", "16:00"))
        tasks.add(Task("Tarea 3", "Lunes", "17:00"))
        tasks.add(Task("Tarea 4", "Lunes", "18:00"))
        tasks.add(Task("Tarea 5", "Lunes", "19:00"))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}