package com.example.todoappmvc.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoappmvc.controller.Controller
import com.example.todoappmvc.R
import com.example.todoappmvc.adapters.CategoryAdapter
import com.example.todoappmvc.adapters.TaskAdapter
import com.example.todoappmvc.databinding.CategoryItemBinding
import com.example.todoappmvc.databinding.EditDialogBinding
import com.example.todoappmvc.databinding.FragmentMainPageBinding
import com.example.todoappmvc.room.database.AppDatabaseBuilder
import com.example.todoappmvc.room.database.DatabaseHelperImpl
import com.example.todoappmvc.room.entity.Category
import com.example.todoappmvc.room.entity.TaskData
import com.example.todoappmvc.utils.ViewmodelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMainPageBinding
    lateinit var viewModel: Controller
    lateinit var mContext: Context
    private var colorList =
        listOf(R.color.light_grey, R.color.green, R.color.red, R.color.yellow, R.color.purple)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    @SuppressLint("NotifyDataSetChanged", "ResourceType", "UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(LayoutInflater.from(requireContext()), container, false)

        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        setupViewModel()
        try {
            loadUIData()
            workUI()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return binding.root
    }
    private fun workUI() {
        try {
            var dialog = Dialog(mContext)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.popup)
            var isclick = false

            binding.fab.setOnClickListener {
                isclick = !isclick
                if (isclick) {
                    binding.dilalog.visibility = View.VISIBLE
                    binding.innerContainer.alpha = 0.2f
                    binding.fab.rotation = 45f
                } else {
                    binding.dilalog.visibility = View.GONE
                    binding.innerContainer.alpha = 1f
                    binding.fab.rotation = 0f
                }
            }
            binding.addTask.setOnClickListener {
                findNavController().navigate(R.id.addTaskFragment)
            }
            binding.option.setOnClickListener {
                binding.option.setImageResource(R.drawable.ic__delete)
            }
            binding.addCategory.setOnClickListener {
                val dialog = Dialog(mContext)
                var color = MutableLiveData<Int>()
                color.value = R.color.black
                dialog.setCancelable(false)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                var view = EditDialogBinding.inflate(
                    LayoutInflater.from(mContext),
                    null,
                    false
                )
                color.observe(viewLifecycleOwner) {
                    view.add.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext, it
                        )
                    )
                }
                val childcount: Int = view.viewgroup.childCount
                for (i in 0 until childcount) {
                    view.viewgroup.getChildAt(i).setOnClickListener {
                        when (it.id) {
                            R.id.black -> {
                                color.value = R.color.black
//                        view.add.setBackgroundColor(R.color.black)
                            }
                            R.id.blue -> {
                                color.value = R.color.blue
//                        view.add.setBackgroundColor(R.color.blue)
                            }
                            R.id.purple -> {
                                color.value = R.color.purple
//                        view.add.setBackgroundColor(R.color.purple)
                            }
                            R.id.yellow -> {
                                color.value = R.color.yellow
//                        view.add.setBackgroundColor(R.color.yellow)
                            }
                            R.id.red -> {
                                color.value = R.color.red
//                        view.add.setBackgroundColor(R.color.red)
                            }
                            R.id.green -> {
                                color.value = R.color.green
//                        view.add.setBackgroundColor(R.color.green)
                            }
                            R.id.grey -> {
                                color.value = R.color.light_grey
//                        view.add.setBackgroundColor(R.color.light_grey)
                            }
                        }
                    }
                }
                color.observe(viewLifecycleOwner) { color ->
                    view.add.setOnClickListener {
                        if (view.edit.text.toString().trim().isEmpty()){
                            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                        }else{
                            viewModel.addCategory(
                                Category(
                                    view.edit.text.toString().trim(),
                                    color
                                )
                            )
                            dialog.dismiss()
                            binding.dilalog.visibility = View.GONE
                            binding.innerContainer.alpha = 1f
                            binding.fab.rotation = 0f
                            loadUIData()
                        }
                    }
                }
                dialog.setContentView(view.root)
                dialog.setCancelable(true)
                dialog.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("NotifyDataSetChanged")
    private fun loadUIData() {
        var tasCount = ArrayList<Int>()
        var today = ArrayList<TaskData>()
        var categoryAdapter = CategoryAdapter()
        try {
            viewModel.getCategoryList().observe(viewLifecycleOwner) {
                it.map { categ ->
                    val getbb = viewModel.getbb(categ.category_id!!)
                    tasCount.add(getbb.size)
                }
                categoryAdapter.context = mContext
                categoryAdapter.tascount = tasCount
                categoryAdapter.list = it
                categoryAdapter.isTask = false
                categoryAdapter.onpress = object : CategoryAdapter.onPress {
                    override fun selected(
                        position: Int,
                        oldItem: Int,
                        list: ArrayList<CategoryItemBinding>,
                        itemview: CategoryItemBinding,
                        category: Category
                    ) {

                    }

                    override fun click(category: Category) {
                        val bundle = Bundle()
                        bundle.putSerializable("data", category)

                        findNavController().navigate(R.id.toDoFragment, bundle)
                    }
                }
                binding.categoryRv.adapter = categoryAdapter
                categoryAdapter.notifyDataSetChanged()
            }
            var completeList = ArrayList<TaskData>()
            var unfulfilledList = ArrayList<TaskData>()
            var reversed = ArrayList<TaskData>()

            viewModel.getAllTaskList().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    completeList.clear()
                    unfulfilledList.clear()
                    it.map { data ->
                        if (data.task_complete == true) {
                            completeList.add(data)
                        } else {
                            unfulfilledList.add(data)
                        }
                    }
                    reversed.clear()
                    val s = unfulfilledList.reversed()
                    reversed.addAll(s)
                    reversed.addAll(if (completeList.isNotEmpty()) completeList.reversed() else emptyList())
                    val df = SimpleDateFormat("dd.MM.yyyy", Locale.US)
                    val date1 = df.format(Calendar.getInstance().time)
                    today.clear()
                    for (i in reversed) {
                        if (date1 == i.task_date) {
                            today.add(i)
                        }
                    }
                    val taskAdapter =
                        TaskAdapter(mContext, requireParentFragment(), today)
                    binding.option.setOnClickListener {
                        taskAdapter.delete()
                        categoryAdapter.notifyDataSetChanged()
                    }
                    binding.taskRv.adapter = taskAdapter
                    taskAdapter.notifyDataSetChanged()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewmodelFactory(DatabaseHelperImpl(AppDatabaseBuilder.getInstance(mContext)))
        )[Controller::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = MainPageFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}