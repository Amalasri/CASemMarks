package com.example.admin.casemmarks.Staff

import android.app.DatePickerDialog
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatRadioButton
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.admin.casemmarks.Database.AppDatabase
import com.example.admin.casemmarks.Database.Daos.LoginDao
import com.example.admin.casemmarks.Database.Daos.StudentBiodataDao
import com.example.admin.casemmarks.Database.Entities.LoginTable
import com.example.admin.casemmarks.Database.Entities.StudentBiodataTable
import com.example.admin.casemmarks.LoginActivity
import com.example.admin.casemmarks.R
import com.example.admin.casemmarks.R2
import java.util.*

class Staff_studentsbiodataentryActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var toolbar: Toolbar
    var studentrollnoTxt: AppCompatTextView? = null
    var studentnameEdt: AppCompatEditText? = null
    var studentdobTxt: AppCompatTextView? = null
    var emailEdt: AppCompatEditText? = null
    var femaleRdo: AppCompatRadioButton? = null
    var maleRdo: AppCompatRadioButton? = null
    var bloodgroupTxt: AppCompatTextView? = null
    var residentialaddressEdt: AppCompatEditText? = null
    var mobilenoEdt: AppCompatEditText? = null
    var fathernameEdt: AppCompatEditText? = null
    var mothernameEdt: AppCompatEditText? = null
    var fatheroccupationEdt: AppCompatEditText? = null
    var motheroccupationEdt: AppCompatEditText? = null
    var fathermobilenoEdt: AppCompatEditText? = null
    var mothermobilenoEdt: AppCompatEditText? = null
    var permanentaddressEdt: AppCompatEditText? = null
    var degreeTxt: AppCompatTextView? = null
    var batchEdt: AppCompatEditText? = null
    var hostelRdo: AppCompatRadioButton? = null
    var dayscallerRdo: AppCompatRadioButton? = null
    var cancelBtn: AppCompatTextView? = null
    var submitBtn: AppCompatTextView? = null

    lateinit var Gender: String
    lateinit var Students_category: String
    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0

    private var studentBiodataDao: StudentBiodataDao? = null
    private val TAG = Staff_studentsbiodataentryActivity::class.java.simpleName

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bloodgroupTxt -> {
                bloodGroup()
            }
            R.id.degreeTxt -> {
                Department()
            }
            R.id.cancelBtn -> {
                Cancelvalues()
            }
            R.id.submitBtn -> {
                formValidation()
                Cancelvalues()
            }
            R.id.studentdobTxt -> {
                DOB()
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_studentsbiodataentry)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        studentrollnoTxt = findViewById(R.id.studentrollnoTxt) as AppCompatTextView
        studentnameEdt = findViewById(R.id.studentnameEdt) as AppCompatEditText
        studentdobTxt = findViewById(R.id.studentdobTxt) as AppCompatTextView
        emailEdt = findViewById(R.id.emailEdt) as AppCompatEditText
        femaleRdo = findViewById(R.id.femaleRdo) as AppCompatRadioButton
        maleRdo = findViewById(R.id.maleRdo) as AppCompatRadioButton
        bloodgroupTxt = findViewById(R.id.bloodgroupTxt) as AppCompatTextView
        residentialaddressEdt = findViewById(R.id.residentialaddressEdt) as AppCompatEditText
        mobilenoEdt = findViewById(R.id.mobilenoEdt) as AppCompatEditText
        fathernameEdt = findViewById(R.id.fathernameEdt) as AppCompatEditText
        mothernameEdt = findViewById(R.id.mothernameEdt) as AppCompatEditText
        fatheroccupationEdt = findViewById(R.id.fatheroccupationEdt) as AppCompatEditText
        motheroccupationEdt = findViewById(R.id.motheroccupationEdt) as AppCompatEditText
        fathermobilenoEdt = findViewById(R.id.fathermobilenoEdt) as AppCompatEditText
        mothermobilenoEdt = findViewById(R.id.mothermobilenoEdt) as AppCompatEditText
        permanentaddressEdt = findViewById(R.id.permanentaddressEdt) as AppCompatEditText
        degreeTxt = findViewById(R.id.degreeTxt) as AppCompatTextView
        batchEdt = findViewById(R.id.batchEdt) as AppCompatEditText
        hostelRdo = findViewById(R.id.hostelRdo) as AppCompatRadioButton
        dayscallerRdo = findViewById(R.id.dayscallerRdo) as AppCompatRadioButton
        cancelBtn = findViewById(R.id.cancelBtn) as AppCompatTextView
        submitBtn = findViewById(R.id.submitBtn) as AppCompatTextView

        toolbar.setTitle(resources.getString(R.string.studentbiodata))
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.setNavigationIcon(resources.getDrawable(R.drawable.ic_back))
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            var name: String = intent.getStringExtra("username")
            var category: String = intent.getStringExtra("category")
            val intent = Intent(this@Staff_studentsbiodataentryActivity, Staff_studentdetailsActivity::class.java)
            intent.putExtra("username", name)
            intent.putExtra("category", category)
            startActivity(intent)
        }

        var db: AppDatabase? = null
        db = Room.databaseBuilder(
            this@Staff_studentsbiodataentryActivity,
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()

        studentBiodataDao = db.StudentBiodataDao()

        val usersList = studentBiodataDao?.getAll()
        var length:Int=usersList!!.size
        Log.e(TAG,length.toString())

        if(length==0)
        {
            studentrollnoTxt!!.setText("16CT001")
        }

        if (usersList != null) {
            for (item in usersList) {
                var length:Int=usersList.size
                studentrollnoTxt!!.setText("16CT00"+(length+1).toString())
                Log.e(TAG,length.toString())
                Log.e(
                    TAG,
                    item.id.toString() + " " + item.StudentName.toString() + " " + item.DOB.toString() + " " + item.Gender
                )
            }
        }

        bloodgroupTxt!!.setOnClickListener(this)
        degreeTxt!!.setOnClickListener(this)
        cancelBtn!!.setOnClickListener(this)
        submitBtn!!.setOnClickListener(this)
        studentdobTxt!!.setOnClickListener(this)
    }

    fun DOB() {
        val imm = getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(studentdobTxt?.getWindowToken(), 0)

        val c = Calendar.getInstance()
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this@Staff_studentsbiodataentryActivity, R.style.MyDatePickerStyle,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                studentdobTxt?.setText(
                    StringBuilder().append(dayOfMonth).append("-").append(month + 1).append("-").append(
                        year
                    )
                )
            }, mYear, mMonth, mDay
        )

        datePickerDialog.show()
    }

    fun bloodGroup() {
        bloodgroupTxt?.requestFocus()
        bloodgroupTxt?.inputType = InputType.TYPE_NULL

        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val bloodgroupview = inflater.inflate(R.layout.bloodgrouplist, null)

        val bloodgrouppopup =
            PopupWindow(bloodgroupview, bloodgroupTxt!!.width, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        bloodgrouppopup.setBackgroundDrawable(resources.getDrawable(R.drawable.edittext_background))
        bloodgrouppopup.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        bloodgrouppopup.isOutsideTouchable = true
        bloodgrouppopup.isFocusable = true
        bloodgrouppopup.showAsDropDown(bloodgroupTxt)
        val bloodlist = bloodgroupview.findViewById(R.id.listview) as ListView
        val adapter = ArrayAdapter<String>(
            this,
            R.layout.bloodgrouplist_itemrow,
            R.id.listTxt,
            resources.getStringArray(R.array.bloodgroup)
        )
        bloodlist.adapter = adapter
        bloodlist.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val items = bloodlist.getItemAtPosition(position).toString()
            bloodgroupTxt?.text = items
            bloodgrouppopup.dismiss()
        }
    }

    fun Department() {
        degreeTxt?.requestFocus()
        degreeTxt?.inputType = InputType.TYPE_NULL

        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val Departmentview = inflater.inflate(R.layout.bloodgrouplist, null)

        val DepartmentPopup =
            PopupWindow(Departmentview, degreeTxt!!.width, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        DepartmentPopup.setBackgroundDrawable(resources.getDrawable(R.drawable.edittext_background))
        DepartmentPopup.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        DepartmentPopup.isOutsideTouchable = true
        DepartmentPopup.isFocusable = true
        DepartmentPopup.showAsDropDown(degreeTxt)
        val bloodlist = Departmentview.findViewById(R.id.listview) as ListView
        val adapter = ArrayAdapter<String>(
            this,
            R.layout.bloodgrouplist_itemrow,
            R.id.listTxt,
            resources.getStringArray(R.array.department)
        )
        bloodlist.adapter = adapter
        bloodlist.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val items = bloodlist.getItemAtPosition(position).toString()
            degreeTxt?.text = items
            DepartmentPopup.dismiss()
        }
    }

    fun Cancelvalues() {
        studentrollnoTxt?.text = ""
        studentnameEdt?.text?.clear()
        studentdobTxt?.text = ""
        emailEdt?.text?.clear()
        if (femaleRdo!!.isChecked)
            femaleRdo!!.isChecked = false
        else
            maleRdo!!.isChecked = false
        bloodgroupTxt?.text = ""
        residentialaddressEdt?.text?.clear()
        mobilenoEdt?.text?.clear()
        fathernameEdt?.text?.clear()
        mothernameEdt?.text?.clear()
        fatheroccupationEdt?.text?.clear()
        motheroccupationEdt?.text?.clear()
        fathermobilenoEdt?.text?.clear()
        mothermobilenoEdt?.text?.clear()
        permanentaddressEdt?.text?.clear()
        batchEdt?.text?.clear()
        degreeTxt?.text = ""
        if (hostelRdo!!.isChecked)
            hostelRdo!!.isChecked = false
        else
            dayscallerRdo!!.isChecked = false
    }

    fun formValidation() {

        if (femaleRdo!!.isChecked)
            Gender = femaleRdo?.text.toString()
        else
            Gender = maleRdo?.text.toString()

        if (hostelRdo!!.isChecked)
            Students_category = hostelRdo?.text.toString()
        else
            Students_category = dayscallerRdo?.text.toString()

        if (studentnameEdt?.text.toString().isEmpty()) {
            ShowToast("Student name cannot be empty")
        } else if (studentdobTxt?.text.toString().isEmpty()) {
            ShowToast("Student DOB cannot be empty")
        } else if (emailEdt?.text.toString().isEmpty()) {
            ShowToast("Emaill cannot be empty")
        } else if (Gender.length == 0) {
            ShowToast("Select any one of the gender option")
        } else if (bloodgroupTxt?.text.toString().isEmpty()) {
            ShowToast("Blood group cannot be empty")
        } else if (residentialaddressEdt?.text.toString().isEmpty()) {
            ShowToast("Residential address cannot be empty")
        } else if (mobilenoEdt?.text.toString().isEmpty()) {
            ShowToast("Mobile no cannot be empty")
        } else if (fathernameEdt?.text.toString().isEmpty()) {
            ShowToast("Father name cannot be empty")
        } else if (mothernameEdt?.text.toString().isEmpty()) {
            ShowToast("Mother name cannot be empty")
        } else if (fatheroccupationEdt?.text.toString().isEmpty()) {
            ShowToast("Father occupation cannot be empty")
        } else if (motheroccupationEdt?.text.toString().isEmpty()) {
            ShowToast("Mother occupation cannot be empty")
        } else if (fathermobilenoEdt?.text.toString().isEmpty()) {
            ShowToast("Father Mobile no cannot be empty")
        } else if (mothermobilenoEdt?.text.toString().isEmpty()) {
            ShowToast("Mother Mobile no cannot be empty")
        } else if (permanentaddressEdt?.text.toString().isEmpty()) {
            ShowToast("Permanent address cannot be empty")
        } else if (degreeTxt?.text.toString().isEmpty()) {
            ShowToast("Department cannot be empty")
        } else if (batchEdt?.text.toString().isEmpty()) {
            ShowToast("Batch cannot be empty")
        } else if (Students_category.length == 0) {
            ShowToast("Select the student category either hostel / dayscaller")
        } else if (mobilenoEdt?.text.toString().length != 10) {
            ShowToast("Invalid contact no")
        } else if (mobilenoEdt?.text.toString().length != 10) {
            ShowToast("Student contact no cannot be invalid")
        } else if (fathermobilenoEdt?.text.toString().length != 10) {
            ShowToast("Father contact no cannot be invalid")
        } else if (mothermobilenoEdt?.text.toString().length != 10) {
            ShowToast("Mother contact no cannot be invalid")
        } else {
            inserttoDb()
        }
    }

    fun ShowToast(s: String) {
        Toast.makeText(this@Staff_studentsbiodataentryActivity, s, Toast.LENGTH_SHORT).show()
    }

    fun inserttoDb() {
        if (femaleRdo!!.isChecked)
            Gender = femaleRdo?.text.toString()
        else
            Gender = maleRdo?.text.toString()

        if (hostelRdo!!.isChecked)
            Students_category = hostelRdo?.text.toString()
        else
            Students_category = dayscallerRdo?.text.toString()

        val studentBiodataTable = StudentBiodataTable()
        studentBiodataTable.StudentRollno = studentrollnoTxt?.text.toString()
        studentBiodataTable.StudentName = studentnameEdt?.text.toString()
        studentBiodataTable.DOB = studentdobTxt?.text.toString()
        studentBiodataTable.Email = emailEdt?.text.toString()
        studentBiodataTable.Gender = Gender
        studentBiodataTable.Bloodgroup = bloodgroupTxt?.text.toString()
        studentBiodataTable.ResidentialAddress = residentialaddressEdt?.text.toString()
        studentBiodataTable.MobileNo = mobilenoEdt?.text.toString()
        studentBiodataTable.FatherName = fathernameEdt?.text.toString()
        studentBiodataTable.MotherName = mothernameEdt?.text.toString()
        studentBiodataTable.FatherOccupation = fatheroccupationEdt?.text.toString()
        studentBiodataTable.MotherOccupation = motheroccupationEdt?.text.toString()
        studentBiodataTable.FatherMobileno = fathermobilenoEdt?.text.toString()
        studentBiodataTable.MotherMobileno = mothermobilenoEdt?.text.toString()
        studentBiodataTable.PermanentAddress = permanentaddressEdt?.text.toString()
        studentBiodataTable.Degree = degreeTxt?.text.toString()
        studentBiodataTable.Batch = batchEdt?.text.toString()
        studentBiodataTable.StudentCategory = Students_category

        studentBiodataDao?.insert(studentBiodataTable)
        ShowToast("Register Successfully")

        /* val usersList = studentBiodataDao?.getAll()
         if (usersList != null) {
             for (item in usersList) {
                 Log.e(
                     TAG,
                     item.id.toString() + " " + item.StudentName.toString() + " " + item.DOB.toString() + " " + item.Gender
                 )
             }
         }*/
    }
}