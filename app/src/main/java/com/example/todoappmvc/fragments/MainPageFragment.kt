package com.example.todoappmvc.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoappmvc.R
import com.example.todoappmvc.databinding.CreateCategoryDialogBinding
import com.example.todoappmvc.databinding.DialogLayoutBinding
import com.example.todoappmvc.databinding.FragmentMainPageBinding
import com.example.todoappmvc.model.room.AppDatabase
import com.example.todoappmvc.model.room.entity.Category

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var category: Category
    private  var colorList= arrayOf("black", "blue","purple", "yellow", "red", "green", "grey")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        appDatabase = AppDatabase.getInstance(requireContext())

        binding.apply {

            add.setOnClickListener {
                val dialog = AlertDialog.Builder(requireContext())
                val bindingDialog = DialogLayoutBinding.inflate(layoutInflater)
                val create = dialog.create()
                create.setView(bindingDialog.root)
                bindingDialog.apply {
                    taskAdd.setOnClickListener {
                        findNavController().navigate(R.id.addTaskFragment)
                        create.dismiss()
                    }
                    listAdd.setOnClickListener {
                        create.dismiss()
                        val dialog = AlertDialog.Builder(requireContext())
                        val bindingDialog = CreateCategoryDialogBinding.inflate(layoutInflater)
                        val create = dialog.create()
                        create.setView(bindingDialog.root)
                        bindingDialog.apply {
                            colorSpinner.adapter =
                                ArrayAdapter(
                                    requireContext(),
                                    com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                                    colorList
                                )

                           save.setOnClickListener {
                               var category_name = categoryName.text.toString()
                               var color_string = colorSpinner.selectedItem.toString()
                               category = Category(categoryName = category_name, categoryColor = color_string)
                               appDatabase.categoryDao().add(category)
                                create.dismiss()
                            }

                        }
                        create.show()
                    }
                }
                create.show()
            }

        }

        return binding.root
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