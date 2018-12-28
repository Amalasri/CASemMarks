package com.example.admin.casemmarks.Staff

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.example.admin.casemmarks.R
import com.example.admin.casemmarks.Staff.Adapter.StepperAdapter
import com.example.admin.casemmarks.Staff.Fragment.Semester2Fragment

class Stafftostudent_Semmarks_entryActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = Stafftostudent_Semmarks_entryActivity::class.java.simpleName
    lateinit var Rollno: String
    lateinit var Category:String
    override fun onClick(v: View) {
        when (v.id) {
            R.id.backTxt -> {
                if (viewpager.currentItem == 0) {
                    // Rollno = getIntent().getStringExtra("rollno")
                    oneTxt.setBackgroundResource(R.drawable.circle_text_design)
                    twoTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    threeTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    backTxt.visibility == View.GONE
                    nextTxt.setText(resources.getString(R.string.next))

                } else if (viewpager.currentItem == 1) {
                    // getStudentRollno()
                    oneTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    twoTxt.setBackgroundResource(R.drawable.circle_text_design)
                    threeTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    backTxt.visibility == View.VISIBLE
                    nextTxt.setText(resources.getString(R.string.next))
                    viewpager.setCurrentItem(0)
                } else if (viewpager.currentItem == 2) {
                    //Rollno = getIntent().getStringExtra("rollno")
                    oneTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    twoTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    threeTxt.setBackgroundResource(R.drawable.circle_text_design)
                    backTxt.visibility == View.VISIBLE
                    nextTxt.setText(resources.getString(R.string.done))
                    viewpager.setCurrentItem(1)
                }
            }
            R.id.nextTxt -> {
                if (viewpager.currentItem == 0) {
                    getStudentRollno()
                    oneTxt.setBackgroundResource(R.drawable.circle_text_design)
                    twoTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    threeTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    backTxt.visibility == View.GONE
                    nextTxt.setText(resources.getString(R.string.next))
                    viewpager.setCurrentItem(1)
                } else if (viewpager.currentItem == 1) {
                    getStudentRollno()
                    oneTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    twoTxt.setBackgroundResource(R.drawable.circle_text_design)
                    threeTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    backTxt.visibility == View.VISIBLE
                    nextTxt.setText(resources.getString(R.string.next))
                    viewpager.setCurrentItem(2)
                } else if (viewpager.currentItem == 2) {
                    //getStudentRollno()
                    oneTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    twoTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    threeTxt.setBackgroundResource(R.drawable.circle_text_design)
                    backTxt.visibility == View.VISIBLE
                    nextTxt.setText(resources.getString(R.string.done))
                }
            }
        }
    }

    lateinit var toolbar: Toolbar
    lateinit var oneTxt: AppCompatTextView
    lateinit var twoTxt: AppCompatTextView
    lateinit var threeTxt: AppCompatTextView
    lateinit var backTxt: AppCompatTextView
    lateinit var nextTxt: AppCompatTextView
    lateinit var viewpager: ViewPager
    lateinit var viewpagercontent:LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stafftostudent__semmarks_entry)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        oneTxt = findViewById(R.id.oneTxt) as AppCompatTextView
        twoTxt = findViewById(R.id.twoTxt) as AppCompatTextView
        threeTxt = findViewById(R.id.threeTxt) as AppCompatTextView
        backTxt = findViewById(R.id.backTxt) as AppCompatTextView
        nextTxt = findViewById(R.id.nextTxt) as AppCompatTextView
        viewpager = findViewById(R.id.viewpager) as ViewPager
        viewpagercontent = findViewById(R.id.viewpagercontent) as LinearLayoutCompat


        backTxt.setOnClickListener(this)
        nextTxt.setOnClickListener(this)

        Rollno = getIntent().getStringExtra("rollno")
        Category=getIntent().getStringExtra("category")
        Log.e(TAG, Rollno)

        toolbar.setTitle(Rollno)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.setNavigationIcon(resources.getDrawable(R.drawable.ic_back))
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        viewpager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                if (viewpager.currentItem == 0) {
                    getStudentRollno()
                    oneTxt.setBackgroundResource(R.drawable.circle_text_design)
                    twoTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    threeTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    backTxt.visibility == View.GONE
                    nextTxt.setText(resources.getString(R.string.next))

                } else if (viewpager.currentItem == 1) {
                    getStudentRollno()
                    oneTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    twoTxt.setBackgroundResource(R.drawable.circle_text_design)
                    threeTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    backTxt.visibility == View.VISIBLE
                    nextTxt.setText(resources.getString(R.string.next))

                } else if (viewpager.currentItem == 2) {
                    //getStudentRollno()
                    oneTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    twoTxt.setBackgroundResource(R.drawable.circle_text_grey_design)
                    threeTxt.setBackgroundResource(R.drawable.circle_text_design)
                    backTxt.visibility == View.VISIBLE
                    nextTxt.setText(resources.getString(R.string.done))
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }
        })

        Rollno = getIntent().getStringExtra("rollno")
        val bundle = Bundle()
        bundle.putString("rollno", Rollno)
        bundle.putString("category",Category)
        viewpager.setAdapter(
            StepperAdapter(
                supportFragmentManager,
                this@Stafftostudent_Semmarks_entryActivity, bundle
            )
        )
    }

    fun getStudentRollno() {
        Rollno = getIntent().getStringExtra("rollno")
        Category=getIntent().getStringExtra("category")
        val bundle = Bundle()
        bundle.putString("rollno", Rollno)
        bundle.putString("category",Category)
        Log.e("rollnochk",Rollno)
    }

    override fun onBackPressed() {

        if (viewpager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewpager.setCurrentItem(viewpager.getCurrentItem() - 1);
        }
    }
/*
    fun Sem2Fragment()
    {
        val transection = fragmentManager.beginTransaction()
        val Semester2Fragment = Semester2Fragment()
        val bundle = Bundle()
        Rollno = getIntent().getStringExtra("rollno")
        bundle.putString("rollno", Rollno)
        Semester2Fragment.setArguments(bundle)
        transection.replace(R.id.viewpagercontent, Semester2Fragment)
        transection.commit();

    }*/

}


