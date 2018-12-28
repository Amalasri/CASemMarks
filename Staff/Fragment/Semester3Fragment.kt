package com.example.admin.casemmarks.Staff.Fragment

import android.arch.persistence.room.Room
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutCompat
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.example.admin.casemmarks.Database.AppDatabase
import com.example.admin.casemmarks.Database.Daos.Semester3Dao
import com.example.admin.casemmarks.Database.Entities.Semester3Table
import com.example.admin.casemmarks.R

class Semester3Fragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.cancelBtn -> {
                datastructureEdt.text?.clear()
                javaEdt.text?.clear()
                javapracticalEdt.text?.clear()
                microprocessorEdt.text?.clear()
                totalTxt.text = ""
                averageTxt.text = ""
            }
            R.id.submitBtn -> {

                if (datastructureEdt.text.toString().isEmpty()) {
                    ShowToast("Data structure subject mark cannot be empty")
                } else if (javaEdt.text.toString().isEmpty()) {
                    ShowToast("Java subject mark cannot be empty")
                } else if (javapracticalEdt.text.toString().isEmpty()) {
                    ShowToast("Java practical program lab mark cannot be empty")
                } else if (microprocessorEdt.text.toString().isEmpty()) {
                    ShowToast("Microprocessor subject mark cannot be empty")
                } else if (TextUtils.isEmpty(totalTxt.text.toString())) {
                    ShowToast(" Total cannot be empty")
                } else if (TextUtils.isEmpty(averageTxt.text.toString())) {
                    ShowToast(" Average cannot be empty")
                } else {
                    inserttoDb()
                    ShowToast("Semester 3 marks registerd Successfully")
                }
            }
            R.id.okBtn -> {


                if (datastructureEdt.text.toString().isEmpty()) {
                    ShowToast("Data structure subject mark cannot be empty")
                } else if (javaEdt.text.toString().isEmpty()) {
                    ShowToast("Java subject mark cannot be empty")
                } else if (javapracticalEdt.text.toString().isEmpty()) {
                    ShowToast("Java practical program lab mark cannot be empty")
                } else if (microprocessorEdt.text.toString().isEmpty()) {
                    ShowToast("Microprocessor subject mark cannot be empty")
                } else {
                    var Datastructure: Double = datastructureEdt.text.toString().toDouble()
                    var Java: Double = javaEdt.text.toString().toDouble()
                    var Javapractical: Double = javapracticalEdt.text.toString().toDouble()
                    var Microprocessor: Double = microprocessorEdt.text.toString().toDouble()

                    var Total: Double = (Datastructure + Java + Javapractical + Microprocessor)
                    totalTxt.setText(Total.toString())

                    var Average: Float = Math.round(Total / 4).toFloat()
                    averageTxt.setText(Average.toString())
                }

            }
        }
    }

    lateinit var datastructureEdt: AppCompatEditText
    lateinit var javaEdt: AppCompatEditText
    lateinit var javapracticalEdt: AppCompatEditText
    lateinit var microprocessorEdt: AppCompatEditText
    lateinit var totalTxt: AppCompatTextView
    lateinit var averageTxt: AppCompatTextView
    lateinit var cancelBtn: AppCompatTextView
    lateinit var submitBtn: AppCompatTextView
    lateinit var okBtn: AppCompatTextView
    var rollno: String = ""
    lateinit var category: String
    lateinit var submitLayout: LinearLayoutCompat
    private var Semester3Dao: Semester3Dao? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_semester3, container, false)

        datastructureEdt = v.findViewById(R.id.datastructureEdt) as AppCompatEditText
        javaEdt = v.findViewById(R.id.javaEdt) as AppCompatEditText
        javapracticalEdt = v.findViewById(R.id.javapracticalEdt) as AppCompatEditText
        microprocessorEdt = v.findViewById(R.id.microprocessorEdt) as AppCompatEditText
        totalTxt = v.findViewById(R.id.totalTxt) as AppCompatTextView
        averageTxt = v.findViewById(R.id.averageTxt) as AppCompatTextView
        cancelBtn = v.findViewById(R.id.cancelBtn) as AppCompatTextView
        submitBtn = v.findViewById(R.id.submitBtn) as AppCompatTextView
        okBtn = v.findViewById(R.id.okBtn) as AppCompatTextView
        submitLayout=v.findViewById(R.id.submitLayout)as LinearLayoutCompat

        cancelBtn.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
        okBtn.setOnClickListener(this)

     /*   rollno = arguments!!.getString("rollno")
        category = arguments!!.getString("category")*/

        if(category=="Student")
        {
            okBtn.visibility=View.GONE
            submitLayout.visibility=View.GONE
            datastructureEdt.setRawInputType(InputType.TYPE_NULL)
            javaEdt.setRawInputType(InputType.TYPE_NULL)
            javapracticalEdt.setRawInputType(InputType.TYPE_NULL)
            microprocessorEdt.setRawInputType(InputType.TYPE_NULL)
        }
        else if(category=="Staff")
        {
            okBtn.visibility=View.VISIBLE
            submitLayout.visibility=View.VISIBLE
        }

        Dbvalueautoretrieve()
        return v;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  rollno = arguments!!.getString("rollno")
        category = arguments!!.getString("category")*/

        val MyPREFERENCES = "MyPrefs"
        val prefs = activity!!.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)

        rollno= prefs.getString("Rollno", null)
        category=prefs.getString("category", null)

        Log.e("category",category)

    }

    fun ShowToast(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    fun DbConnection() {

        var db: AppDatabase? = null
        db = Room.databaseBuilder(
            this!!.activity!!,
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()

        Semester3Dao = db.Semester3Dao()
    }

    fun inserttoDb() {
        DbConnection()
        val usersList = Semester3Dao?.getAll()
        if (usersList?.size != 0) {
            if (usersList != null) {
                for (item in usersList) {
                    if (item.StudentRollno == rollno) {
                        ShowToast("Already Inserted")
                    }
                    val Semester3Table = Semester3Table()
                    Semester3Table.StudentRollno = rollno
                    Semester3Table.DataStructure = datastructureEdt.text.toString()
                    Semester3Table.Java = javaEdt.text.toString()
                    Semester3Table.JavaPractical = javapracticalEdt.text.toString()
                    Semester3Table.MicroProcessor = microprocessorEdt.text.toString()
                    Semester3Table.Total = totalTxt.text.toString()
                    Semester3Table.Average = averageTxt.text.toString()

                    Semester3Dao?.insert(Semester3Table)
                }
            }
        }

        if (usersList?.size == 0) {
            val Semester3Table = Semester3Table()
            Semester3Table.StudentRollno = rollno
            Semester3Table.DataStructure = datastructureEdt.text.toString()
            Semester3Table.Java = javaEdt.text.toString()
            Semester3Table.JavaPractical = javapracticalEdt.text.toString()
            Semester3Table.MicroProcessor = microprocessorEdt.text.toString()
            Semester3Table.Total = totalTxt.text.toString()
            Semester3Table.Average = averageTxt.text.toString()

            Semester3Dao?.insert(Semester3Table)
        }
    }

    fun Dbvalueautoretrieve() {
        DbConnection()
        val usersList = Semester3Dao?.getAll()
        if (usersList != null) {
            for (item in usersList) {
                if (item.StudentRollno == rollno) {
                    datastructureEdt.setText(item.DataStructure)
                    javaEdt.setText(item.Java)
                    javapracticalEdt.setText(item.JavaPractical)
                    microprocessorEdt.setText(item.MicroProcessor)
                    totalTxt.setText(item.Total)
                    averageTxt.setText(item.Average)

                }
            }
        }
    }



}
