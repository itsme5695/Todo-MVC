package com.example.todoappmvc.bottomSheet

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import com.example.todoappmvc.R
import com.example.todoappmvc.model.room.AppDatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TimeSheet : BottomSheetDialogFragment() {
    lateinit var appDatabase: AppDatabase
    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener = object : TimePickerDialog.OnTimeSetListener {
        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

            // logic to properly handle
            // the picked timings by user
            val formattedTime: String = when {
                hourOfDay == 0 -> {
                    if (minute < 10) {
                        "${hourOfDay + 12}:0${minute} am"
                    } else {
                        "${hourOfDay + 12}:${minute} am"
                    }
                }
                hourOfDay > 12 -> {
                    if (minute < 10) {
                        "${hourOfDay - 12}:0${minute} pm"
                    } else {
                        "${hourOfDay - 12}:${minute} pm"
                    }
                }
                hourOfDay == 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute} pm"
                    } else {
                        "${hourOfDay}:${minute} pm"
                    }
                }
                else -> {
                    if (minute < 10) {
                        "${hourOfDay}:${minute} am"
                    } else {
                        "${hourOfDay}:${minute} am"
                    }
                }
            }
            //--------------set to textview
            // previewSelectedTimeTextView.text = formattedTime
        }
    }

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(
            R.layout.time_picker_layout, container, false
        )
        appDatabase = AppDatabase.getInstance(requireContext())
        val time_picker: TimePicker = v.findViewById(R.id.time_view)
        val save_btn: Button = v.findViewById(R.id.save)
        val cancel_btn: Button = v.findViewById(R.id.cancel)


         val timePickerDialogListener: TimePickerDialog.OnTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
             // logic to properly handle
             // the picked timings by user
             val formattedTime: String = when {
                 hourOfDay == 0 -> {
                     if (minute < 10) {
                         "${hourOfDay + 12}:0${minute} am"
                     } else {
                         "${hourOfDay + 12}:${minute} am"
                     }
                 }
                 hourOfDay > 12 -> {
                     if (minute < 10) {
                         "${hourOfDay - 12}:0${minute} pm"
                     } else {
                         "${hourOfDay - 12}:${minute} pm"
                     }
                 }
                 hourOfDay == 12 -> {
                     if (minute < 10) {
                         "${hourOfDay}:0${minute} pm"
                     } else {
                         "${hourOfDay}:${minute} pm"
                     }
                 }
                 else -> {
                     if (minute < 10) {
                         "${hourOfDay}:${minute} am"
                     } else {
                         "${hourOfDay}:${minute} am"
                     }
                 }
             }
             //--------------set to textview
             save_btn.setOnClickListener {
                 // previewSelectedTimeTextView.text = formattedTime

             }

             cancel_btn.setOnClickListener {
                 dismiss()
             }
         }


        return v
    }
}