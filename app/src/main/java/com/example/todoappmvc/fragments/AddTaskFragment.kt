package com.example.todoappmvc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoappmvc.bottomSheet.CalendarSheet
import com.example.todoappmvc.bottomSheet.TimeSheet
import com.example.todoappmvc.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentAddTaskBinding
private lateinit var calendar_view: CalendarView

class AddTaskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FragmentAddTaskBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        calendar_view = CalendarView(requireContext())
        binding.apply {
            cancel.setOnClickListener { findNavController().popBackStack() }
            calendarImg.setOnClickListener {

                    val bottomSheet = CalendarSheet()
                    bottomSheet.show(childFragmentManager, "CalendarBottomSheet")


            }

            timeImg.setOnClickListener {
                val bottomSheet = TimeSheet()
                bottomSheet.show(childFragmentManager, "TimeBottomSheet")

            }


        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = AddTaskFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}