package com.example.admin.casemmarks.Staff.Adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import com.example.admin.casemmarks.Staff.Fragment.Semester1Fragment
import com.example.admin.casemmarks.Staff.Fragment.Semester2Fragment
import com.example.admin.casemmarks.Staff.Fragment.Semester3Fragment

class StepperAdapter(
    fm: FragmentManager,
    myContext: Context,
    private var bundle: Bundle
) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                val semester1fragment = Semester1Fragment()
                semester1fragment.arguments = bundle
                return semester1fragment
            }
            1 -> {
               /* val semester2fragment = Semester2Fragment()
                semester2fragment.arguments = bundle
                Log.e("rollnobundle", bundle.toString())
                Log.e("rollnobundle", semester2fragment.arguments.toString())*/
                return Semester2Fragment()
            }

            2 -> {
                return Semester3Fragment()
            }
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 3
    }

}


