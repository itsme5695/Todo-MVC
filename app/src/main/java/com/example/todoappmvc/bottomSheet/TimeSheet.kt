package com.example.todoappmvc.bottomSheet

import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todoappmvc.R
import com.example.todoappmvc.model.room.AppDatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.math.min

class TimeSheet : BottomSheetDialogFragment() {
    lateinit var appDatabase: AppDatabase
    private var format = ""
    private var time: String = ""

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(
            R.layout.time_picker_layout, container, false
        )
        appDatabase = AppDatabase.getInstance(requireContext())
        val time_picker: TimePicker = v.findViewById(R.id.time_view)
        val save_btn: Button = v.findViewById(R.id.save)
        val cancel_btn: Button = v.findViewById(R.id.cancel)
        save_btn.setOnClickListener {
            var hour = time_picker.hour
            var minute = time_picker.minute
            showTime(hour, minute)

            Toast.makeText(requireContext(), time, Toast.LENGTH_SHORT).show()
            dismiss()
        }
        cancel_btn.setOnClickListener {
            dismiss()
        }


        return v
    }

    fun showTime(hour: Int, min: Int):String {
        var hour = hour
        if (hour == 0) {
            hour += 12
            format = "AM"
        } else if (hour == 12) {
            format = "PM"
        } else if (hour > 12) {
            hour -= 12
            format = "PM"
        } else {
            format = "AM"
        }
        time = StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format).toString()
        return time

    }
}