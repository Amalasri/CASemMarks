package com.example.admin.casemmarks.Staff

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.example.admin.casemmarks.LoginActivity
import com.example.admin.casemmarks.R
import com.example.admin.casemmarks.Database.AppDatabase
import android.arch.persistence.room.Room
import android.support.annotation.Nullable
import android.support.v7.widget.*
import com.example.admin.casemmarks.Database.Daos.StudentBiodataDao
import android.widget.LinearLayout
import com.example.admin.casemmarks.Database.Entities.StudentBiodataTable
import com.example.admin.casemmarks.Staff.Adapter.Staff_studentdetailsAdapter
import java.util.*

class Staff_studentdetailsActivity : AppCompatActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    private val TAG = Staff_studentdetailsActivity::class.java.simpleName
    @BindView(R.id.toolbar)
    lateinit var toolbar: android.support.v7.widget.Toolbar
    @BindView(R.id.drawerlayout)
    lateinit var drawerlayout: DrawerLayout
    @BindView(R.id.navigationview)
    lateinit var navigationview: NavigationView
    @BindView(R.id.addstudentFab)
    lateinit var addstudentFab: FloatingActionButton
    @BindView(R.id.studentdetailsRecyclerview)
    lateinit var studentdetailsRecyclerview: RecyclerView
    lateinit var category: String

    var values = ArrayList<String>()
    private var studentBiodataDao: StudentBiodataDao? = null

    override fun onClick(v: View) {
        when (v.id) {
            R.id.addstudentFab -> {
                Log.e(TAG, "check")
                var name: String = intent.getStringExtra("username")
                var category: String = intent.getStringExtra("category")
                val intent = Intent(this@Staff_studentdetailsActivity, Staff_studentsbiodataentryActivity::class.java)

                intent.putExtra("username", name)
                intent.putExtra("category", category)
                startActivity(intent)
            }
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_studentdetails)

        ButterKnife.bind(this)

        toolbar.setTitle(resources.getText(R.string.studentdetails))
        toolbar.setTitleTextColor(resources.getColor(R.color.white))

        var name: String = intent.getStringExtra("username")
        category = intent.getStringExtra("category")

        if(category=="Student")
        {
            addstudentFab.visibility=View.INVISIBLE
        }
        else if(category=="Staff")
        {
            addstudentFab.visibility=View.VISIBLE
        }

        Log.e(TAG, name + " " + category)

        val toggle = ActionBarDrawerToggle(
            this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationview.setNavigationItemSelectedListener(this)
        toggle.getDrawerArrowDrawable().setColor(resources.getColor(R.color.white))

        val navigation = navigationview.inflateHeaderView(R.layout.nav_header_main)
        val nameTxt = navigation.findViewById(R.id.nameTxt) as AppCompatTextView
        val categoryTxt = navigation.findViewById(R.id.categoryTxt) as AppCompatTextView

        nameTxt.setText(name)
        categoryTxt.setText(category)

        addstudentFab.setOnClickListener(this)

        var db: AppDatabase? = null
        db = Room.databaseBuilder(
            this@Staff_studentdetailsActivity,
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()

        studentBiodataDao = db.StudentBiodataDao()

        if (category.equals("Staff")) {

            val studentdetails = studentBiodataDao?.getAll()

            if (studentdetails != null) {
                for (item in studentdetails) {
                    Log.e(
                        TAG,
                        item.id.toString()
                                + " " + item.StudentName.toString()
                                + " " + item.DOB.toString()
                                + " " + item.Gender
                    )
                }
            }

            Log.e(TAG, studentdetails?.size.toString())

            val studentadapter =
                Staff_studentdetailsAdapter(studentdetails as ArrayList<StudentBiodataTable>, category)
            // val studentadapter = Staff_studentdetailsAdapter(studentdetails)
            studentdetailsRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            studentdetailsRecyclerview.setItemAnimator(DefaultItemAnimator())
            studentdetailsRecyclerview.adapter = studentadapter

            for (item in studentdetails) {
                Log.e(TAG, values.add(item.id.toString()).toString() + " " + values.add(item.StudentName.toString()))
            }
        }

        if (category.equals("Student")) {

            val studentdetails = studentBiodataDao?.findstudent(name)

            if (studentdetails != null) {
                for (item in studentdetails) {
                    Log.e(
                        TAG,
                        item.id.toString()
                                + " " + item.StudentName.toString()
                                + " " + item.DOB.toString()
                                + " " + item.Gender
                    )
                }
            }

            Log.e(TAG, studentdetails?.size.toString())

            val studentadapter =
                Staff_studentdetailsAdapter(studentdetails as ArrayList<StudentBiodataTable>, category)
            // val studentadapter = Staff_studentdetailsAdapter(studentdetails)
            studentdetailsRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            studentdetailsRecyclerview.setItemAnimator(DefaultItemAnimator())
            studentdetailsRecyclerview.adapter = studentadapter

            for (item in studentdetails) {
                Log.e(TAG, values.add(item.id.toString()).toString() + " " + values.add(item.StudentName.toString()))
            }
        }
    }

    override fun onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu)
        return true
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val id = p0.getItemId()
        if (id == R.id.logout) {
            val intent = Intent(this@Staff_studentdetailsActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }
}
