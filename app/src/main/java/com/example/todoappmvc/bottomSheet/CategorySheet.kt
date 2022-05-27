package com.example.todoappmvc.bottomSheet

import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.todoappmvc.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CategorySheet: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(
            R.layout.categories_layout, container, false
        )
        val inbox: CardView = v.findViewById(R.id.inbox)


        return v
    }


}