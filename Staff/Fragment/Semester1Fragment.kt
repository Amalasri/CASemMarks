package com.example.admin.casemmarks.Staff.Fragment

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent.getIntent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.admin.casemmarks.Database.AppDatabase
import com.example.admin.casemmarks.Database.Daos.Semester1Dao
import com.example.admin.casemmarks.Database.Entities.Semester1Table
import com.example.admin.casemmarks.R
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.R.id.edit
import android.support.v7.widget.LinearLayoutCompat
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import android.app.Activity
import android.support.v4.content.ContextCompat.getSystemService
import android.text.InputType
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager


class Semester1Fragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cancelBtn -> {
                clear()
                // Retrivedata()
            }
            R.id.submitBtn -> {
                if (tamilEdt.text.toString().isEmpty()) {
                    ShowToast("Tamil subject mark cannot be empty")
                } else if (englishEdt.text.toString().isEmpty()) {
                    ShowToast("English subject mark cannot be empty")
                } else if (cprogramEdt.text.toString().isEmpty()) {
                    ShowToast("C program subject mark cannot be empty")
                } else if (mathsEdt.text.toString().isEmpty()) {
                    ShowToast("Maths subject mark cannot be empty")
                } else if (digitalEdt.text.toString().isEmpty()) {
                    ShowToast("Digital subject mark cannot be empty")
                } else if (cpracticalEdt.text.toString().isEmpty()) {
                    ShowToast("C practical lab mark cannot be empty")
                } else if (totalTxt.text.toString().isEmpty()) {
                    ShowToast("Total cannot be empty")
                } else if (averageTxt.text.toString().isEmpty()) {
                    ShowToast("Average cannot be empty")
                } else {
                    inserttoDb()
                    ShowToast("Semester 1 marks registerd Successfully")
                    clear()
                }
            }
            R.id.okBtn -> {

                if (tamilEdt.text.toString().isEmpty()) {
                    ShowToast("Tamil subject mark cannot be empty")
                } else if (englishEdt.text.toString().isEmpty()) {
                    ShowToast("English subject mark cannot be empty")
                } else if (cprogramEdt.text.toString().isEmpty()) {
                    ShowToast("C program subject mark cannot be empty")
                } else if (mathsEdt.text.toString().isEmpty()) {
                    ShowToast("Maths subject mark cannot be empty")
                } else if (digitalEdt.text.toString().isEmpty()) {
                    ShowToast("Digital subject mark cannot be empty")
                } else if (cpracticalEdt.text.toString().isEmpty()) {
                    ShowToast("C practical lab mark cannot be empty")
                } else {
                    var Tamil: Double = tamilEdt.text.toString().toDouble()
                    var English: Double = englishEdt.text.toString().toDouble()
                    var C: Double = cprogramEdt.text.toString().toDouble()
                    var Maths: Double = mathsEdt.text.toString().toDouble()
                    var Digital: Double = digitalEdt.text.toString().toDouble()
                    var CPractical: Double = cpracticalEdt.text.toString().toDouble()

                    var Total: Double = (Tamil + English + C + Maths + Digital + CPractical)
                    totalTxt.setText(Total.toString())

                    var Average: Float = Math.round(Total / 6).toFloat()
                    averageTxt.setText(Average.toString())
                }

            }
        }
    }

    lateinit var tamilEdt: AppCompatEditText
    lateinit var englishEdt: AppCompatEditText
    lateinit var cprogramEdt: AppCompatEditText
    lateinit var mathsEdt: AppCompatEditText
    lateinit var digitalEdt: AppCompatEditText
    lateinit var cpracticalEdt: AppCompatEditText
    lateinit var totalTxt: AppCompatTextView
    lateinit var averageTxt: AppCompatTextView
    lateinit var cancelBtn: AppCompatTextView
    lateinit var submitBtn: AppCompatTextView
    lateinit var okBtn: AppCompatTextView
    lateinit var submitLayout: LinearLayoutCompat

    private var Semester1Dao: Semester1Dao? = null
    private val TAG = context
    var studentid: Long = 0
    var rollno: String = ""
    lateinit var category: String

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_semester1, container, false)

        tamilEdt = v.findViewById(R.id.tamilEdt) as AppCompatEditText
        englishEdt = v.findViewById(R.id.englishEdt) as AppCompatEditText
        cprogramEdt = v.findViewById(R.id.cprogramEdt) as AppCompatEditText
        mathsEdt = v.findViewById(R.id.mathsEdt) as AppCompatEditText
        digitalEdt = v.findViewById(R.id.digitalEdt) as AppCompatEditText
        cpracticalEdt = v.findViewById(R.id.cpracticalEdt) as AppCompatEditText
        totalTxt = v.findViewById(R.id.totalTxt) as AppCompatTextView
        averageTxt = v.findViewById(R.id.averageTxt) as AppCompatTextView
        cancelBtn = v.findViewById(R.id.cancelBtn) as AppCompatTextView
        submitBtn = v.findViewById(R.id.submitBtn) as AppCompatTextView
        okBtn = v.findViewById(R.id.okBtn) as AppCompatTextView
        submitLayout=v.findViewById(R.id.submitLayout)as LinearLayoutCompat

        cancelBtn.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
        okBtn.setOnClickListener(this)


        rollno = arguments!!.getString("rollno")
        category = arguments!!.getString("category")

        if(category=="Student")
        {
            okBtn.visibility=View.GONE
            submitLayout.visibility=View.GONE
            tamilEdt.setRawInputType(InputType.TYPE_NULL)
            englishEdt.setRawInputType(InputType.TYPE_NULL)
            cprogramEdt.setRawInputType(InputType.TYPE_NULL)
            mathsEdt.setRawInputType(InputType.TYPE_NULL)
            digitalEdt.setRawInputType(InputType.TYPE_NULL)
            cpracticalEdt.setRawInputType(InputType.TYPE_NULL)
            //getActivity()!!.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
        else if(category=="Staff")
        {
            okBtn.visibility=View.VISIBLE
            submitLayout.visibility=View.VISIBLE
        }
        Dbvalueautoretrieve()

        return v;
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

        Semester1Dao = db.Semester1Dao()
    }

    fun inserttoDb() {
        DbConnection()

        Log.e("db", "dbconnection")

        val usersList = Semester1Dao?.getAll()
        if (usersList != null) {
            for (item in usersList) {
                if (item.StudentRollno!!.toString() == rollno) {
                    ShowToast("Already Inserted")
                } else if (item.StudentRollno!!.toString() != rollno) {
                    rollno = arguments!!.getString("rollno")
                    val Semester1Table = Semester1Table()
                    Semester1Table.StudentRollno = rollno
                    Semester1Table.Tamil = tamilEdt.text.toString()
                    Semester1Table.English = englishEdt.text.toString()
                    Semester1Table.Cprogram = cpracticalEdt.text.toString()
                    Semester1Table.Maths = mathsEdt.text.toString()
                    Semester1Table.Digital = digitalEdt.text.toString()
                    Semester1Table.Cpractical = cpracticalEdt.text.toString()
                    Semester1Table.Total = totalTxt.text.toString()
                    Semester1Table.Average = averageTxt.text.toString()
                    Semester1Dao?.insert(Semester1Table)
                }
            }
        }

        Log.e("userlistsize", usersList?.size.toString())

        if (usersList?.size == 0) {
            rollno = arguments!!.getString("rollno")
            Log.e("null 1", "insert")
            val Semester1Table = Semester1Table()
            Semester1Table.StudentRollno = rollno
            Semester1Table.Tamil = tamilEdt.text.toString()
            Semester1Table.English = englishEdt.text.toString()
            Semester1Table.Cprogram = cpracticalEdt.text.toString()
            Semester1Table.Maths = mathsEdt.text.toString()
            Semester1Table.Digital = digitalEdt.text.toString()
            Semester1Table.Cpractical = cpracticalEdt.text.toString()
            Semester1Table.Total = totalTxt.text.toString()
            Semester1Table.Average = averageTxt.text.toString()
            Semester1Dao?.insert(Semester1Table)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rollno = arguments!!.getString("rollno")
        category = arguments!!.getString("category")

      /*  if(category=="Student")
        {
            okBtn.visibility=View.GONE
            submitLayout.visibility=View.GONE
        }
        else if(category=="Staff")
        {
            okBtn.visibility=View.VISIBLE
            submitLayout.visibility=View.VISIBLE
        }
*/
        Log.e("rollsem1", rollno)

        val sharedpreferences: SharedPreferences
        val MyPREFERENCES = "MyPrefs"
        sharedpreferences = activity!!.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)

        val editor = sharedpreferences.edit()
        editor.putString("Rollno", rollno)
        editor.putString("category", category)
        editor.commit()


    }

    fun Retrivedata() {
        DbConnection()
        rollno = arguments!!.getString("rollno")

        val usersList = Semester1Dao?.findBySemester1(rollno)
        Log.e("userlistsize", usersList?.size.toString())
        if (usersList != null) {
            Log.e("cancel", rollno)
            for (item in usersList) {
                Log.e(
                    "Semester1Fragment",
                    item.id.toString() + " " + item.StudentRollno.toString() + " " + item.Tamil.toString()
                            + " " + item.English + " " + item.Maths + " " + item.Cprogram
                )
                tamilEdt.setText(item.Tamil)
                englishEdt.setText(item.English)
                cprogramEdt.setText(item.Cprogram)
                mathsEdt.setText(item.Maths)
                digitalEdt.setText(item.Digital)
                cpracticalEdt.setText(item.Cpractical)
                totalTxt.setText(item.Total)
                averageTxt.setText(item.Average)
            }
        }
    }

    fun clear() {
        tamilEdt.text?.clear()
        englishEdt.text?.clear()
        cprogramEdt.text?.clear()
        mathsEdt.text?.clear()
        digitalEdt.text?.clear()
        cpracticalEdt.text?.clear()
        totalTxt.text = ""
        averageTxt.text = ""
    }

    fun Dbvalueautoretrieve() {
        DbConnection()
        val usersList = Semester1Dao?.getAll()
        if (usersList != null) {
            for (item in usersList) {
                if (item.StudentRollno!!.toString() == rollno) {
                    tamilEdt.setText(item.Tamil)
                    englishEdt.setText(item.English)
                    cprogramEdt.setText(item.Cprogram)
                    mathsEdt.setText(item.Maths)
                    digitalEdt.setText(item.Digital)
                    cpracticalEdt.setText(item.Cpractical)
                    totalTxt.setText(item.Total)
                    averageTxt.setText(item.Average)

                }
            }
        }
    }
}