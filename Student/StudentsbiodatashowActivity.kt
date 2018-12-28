package com.example.admin.casemmarks.Student

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatRadioButton
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import com.example.admin.casemmarks.R
import com.example.admin.casemmarks.Staff.Staff_studentdetailsActivity

class StudentsbiodatashowActivity : AppCompatActivity() {

    var studentnameTxt: AppCompatTextView? = null
    var rollnoTxt: AppCompatTextView? = null
    var batchTxt: AppCompatTextView? = null
    var genderTxt: AppCompatTextView? = null
    var ponenoTxt: AppCompatTextView? = null
    var emailTxt: AppCompatTextView? = null
    var dobTxt: AppCompatTextView? = null
    var degreeTxt: AppCompatTextView? = null
    var bloodgroupTxt: AppCompatTextView? = null
    var residentialaddressTxt: AppCompatTextView? = null
    var fathernameTxt: AppCompatTextView? = null
    var mothernameTxt: AppCompatTextView? = null
    var fatheroccupationTxt: AppCompatTextView? = null
    var motheroccupationTxt: AppCompatTextView? = null
    var mothermobilenoTxt: AppCompatTextView? = null
    var permanentaddressTxt: AppCompatTextView? = null
    var fathermobilenoTxt: AppCompatTextView? = null
    var categoryTxt: AppCompatTextView? = null
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentsbiodatashow)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        studentnameTxt = findViewById(R.id.studentnameTxt) as AppCompatTextView
        rollnoTxt = findViewById(R.id.rollnoTxt) as AppCompatTextView
        batchTxt = findViewById(R.id.batchTxt) as AppCompatTextView
        genderTxt = findViewById(R.id.genderTxt) as AppCompatTextView
        ponenoTxt = findViewById(R.id.mobilenoTxt) as AppCompatTextView
        dobTxt = findViewById(R.id.dobTxt) as AppCompatTextView
        emailTxt = findViewById(R.id.emailTxt) as AppCompatTextView
        degreeTxt = findViewById(R.id.degreeTxt) as AppCompatTextView
        bloodgroupTxt = findViewById(R.id.bloodgroupTxt) as AppCompatTextView
        residentialaddressTxt = findViewById(R.id.residentialaddressTxt) as AppCompatTextView
        fathernameTxt = findViewById(R.id.fathernameTxt) as AppCompatTextView
        mothernameTxt = findViewById(R.id.mothernameTxt) as AppCompatTextView
        fatheroccupationTxt = findViewById(R.id.fatheroccupationTxt) as AppCompatTextView
        motheroccupationTxt = findViewById(R.id.motheroccupationTxt) as AppCompatTextView
        fathermobilenoTxt = findViewById(R.id.fathermobilenoTxt) as AppCompatTextView
        mothermobilenoTxt = findViewById(R.id.mothermobilenoTxt) as AppCompatTextView
        permanentaddressTxt = findViewById(R.id.permanentaddressTxt) as AppCompatTextView
        categoryTxt = findViewById(R.id.categoryTxt) as AppCompatTextView


        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.setNavigationIcon(resources.getDrawable(R.drawable.ic_back))
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        studentnameTxt?.setText(getIntent().getStringExtra("studentname"))
        rollnoTxt?.setText(getIntent().getStringExtra("rollno"))
        batchTxt?.setText(getIntent().getStringExtra("batch"))
        genderTxt?.setText(getIntent().getStringExtra("gender"))
        ponenoTxt?.setText(getIntent().getStringExtra("mobileno"))
        emailTxt?.setText(getIntent().getStringExtra("email"))
        dobTxt?.setText(getIntent().getStringExtra("dob"))
        degreeTxt?.setText(getIntent().getStringExtra("degree"))
        bloodgroupTxt?.setText(getIntent().getStringExtra("bloodgroup"))
        residentialaddressTxt?.setText(getIntent().getStringExtra("residentialaddress"))
        fathernameTxt?.setText(getIntent().getStringExtra("fathername"))
        mothernameTxt?.setText(getIntent().getStringExtra("mothername"))
        fatheroccupationTxt?.setText(getIntent().getStringExtra("fatheroccupation"))
        motheroccupationTxt?.setText(getIntent().getStringExtra("motheroccupation"))
        fathermobilenoTxt?.setText(getIntent().getStringExtra("fathermobileno"))
        mothermobilenoTxt?.setText(getIntent().getStringExtra("mothermobileno"))
        permanentaddressTxt?.setText(getIntent().getStringExtra("permanentaddress"))
        categoryTxt?.setText(getIntent().getStringExtra("studentcategory"))
    }
}
