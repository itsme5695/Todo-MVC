package com.example.todoappmvc.bottomSheet

import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import com.example.todoappmvc.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CalendarSheet: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(
            R.layout.calendar_layout, container, false
        )
        val calendar_view: CalendarView = v.findViewById(R.id.calendar_view)
        calendar_view.setOnDateChangeListener { view, year, month, date ->
            Toast.makeText(
                requireContext(),
                "$date/$month/$year",
                Toast.LENGTH_SHORT
            ).show();
        } //m

        return v
    }
}