package com.example.admin.casemmarks.Staff.Fragment

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.admin.casemmarks.Database.AppDatabase
import com.example.admin.casemmarks.Database.Daos.Semester2Dao
import com.example.admin.casemmarks.Database.Entities.Semester2Table
import com.example.admin.casemmarks.R
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.support.v7.widget.LinearLayoutCompat
import android.text.InputType
import android.view.WindowManager


class Semester2Fragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cancelBtn -> {
                Clear()
            }
            R.id.submitBtn -> {
                if (tamilEdt.text.toString().isEmpty()) {
                    ShowToast("Tamil subject mark cannot be empty")
                } else if (englishEdt.text.toString().isEmpty()) {
                    ShowToast("English subject mark cannot be empty")
                } else if (oopsEdt.text.toString().isEmpty()) {
                    ShowToast("Oops program subject mark cannot be empty")
                } else if (discretemathsEdt.text.toString().isEmpty()) {
                    ShowToast("Discrete maths subject mark cannot be empty")
                } else if (oopspracticalEdt.text.toString().isEmpty()) {
                    ShowToast("Oops practical lab mark cannot be empty")
                } else if (internetbasicsEdt.text.toString().isEmpty()) {
                    ShowToast("Internet basics lab mark cannot be empty")
                } else if (totalTxt.text.toString().isEmpty()) {
                    ShowToast("Total cannot be empty")
                } else if (averageTxt.text.toString().isEmpty()) {
                    ShowToast("Average cannot be empty")
                } else {
                    InserttoDb()
                    ShowToast("Semester 2 marks registerd Successfully")
                }
            }
            R.id.okBtn -> {

                if (tamilEdt.text.toString().isEmpty()) {
                    ShowToast("Tamil subject mark cannot be empty")
                } else if (englishEdt.text.toString().isEmpty()) {
                    ShowToast("English subject mark cannot be empty")
                } else if (oopsEdt.text.toString().isEmpty()) {
                    ShowToast("Oops program subject mark cannot be empty")
                } else if (discretemathsEdt.text.toString().isEmpty()) {
                    ShowToast("Discrete maths subject mark cannot be empty")
                } else if (oopspracticalEdt.text.toString().isEmpty()) {
                    ShowToast("Oops practical lab mark cannot be empty")
                } else if (internetbasicsEdt.text.toString().isEmpty()) {
                    ShowToast("Internet basics lab mark cannot be empty")
                } else {
                    var Tamil: Double = tamilEdt.text.toString().toDouble()
                    var English: Double = englishEdt.text.toString().toDouble()
                    var Oops: Double = oopsEdt.text.toString().toDouble()
                    var Discretemaths: Double = discretemathsEdt.text.toString().toDouble()
                    var Oopspractical: Double = oopspracticalEdt.text.toString().toDouble()
                    var Internetbasics: Double = internetbasicsEdt.text.toString().toDouble()

                    var Total: Double = (Tamil + English + Oops + Discretemaths + Oopspractical + Internetbasics)
                    totalTxt.setText(Total.toString())

                    var Average: Float = Math.round(Total / 6).toFloat()
                    averageTxt.setText(Average.toString())
                }


            }
        }
    }

    lateinit var tamilEdt: AppCompatEditText
    lateinit var englishEdt: AppCompatEditText
    lateinit var oopsEdt: AppCompatEditText
    lateinit var discretemathsEdt: AppCompatEditText
    lateinit var oopspracticalEdt: AppCompatEditText
    lateinit var internetbasicsEdt: AppCompatEditText
    lateinit var totalTxt: AppCompatTextView
    lateinit var averageTxt: AppCompatTextView
    lateinit var cancelBtn: AppCompatTextView
    lateinit var submitBtn: AppCompatTextView
    lateinit var okBtn: AppCompatTextView
    lateinit var submitLayout: LinearLayoutCompat
    var rollno: String = ""
    private var Semester2Dao: Semester2Dao? = null
    lateinit var category:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_semester2, container, false)

        tamilEdt = v.findViewById(R.id.tamilEdt) as AppCompatEditText
        englishEdt = v.findViewById(R.id.englishEdt) as AppCompatEditText
        oopsEdt = v.findViewById(R.id.oopsEdt) as AppCompatEditText
        discretemathsEdt = v.findViewById(R.id.discretemathsEdt) as AppCompatEditText
        oopspracticalEdt = v.findViewById(R.id.oopspracticalEdt) as AppCompatEditText
        internetbasicsEdt = v.findViewById(R.id.internetbasicsEdt) as AppCompatEditText
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
            tamilEdt.setRawInputType(InputType.TYPE_NULL)
            englishEdt.setRawInputType(InputType.TYPE_NULL)
            oopsEdt.setRawInputType(InputType.TYPE_NULL)
            discretemathsEdt.setRawInputType(InputType.TYPE_NULL)
            oopspracticalEdt.setRawInputType(InputType.TYPE_NULL)
            internetbasicsEdt.setRawInputType(InputType.TYPE_NULL)
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
        val prefs = activity!!.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE)

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

        Semester2Dao = db.Semester2Dao()
    }

    fun InserttoDb() {
        DbConnection()
        Log.e("Rollno", rollno)
        val usersList = Semester2Dao?.getAll()

        if (usersList?.size != 0) {
            if (usersList != null) {
                for (item in usersList) {
                    if (item.StudentRollno!!.toString() == rollno) {
                        ShowToast("Already Inserted")
                    } else if (item.StudentRollno!!.toString() != rollno) {
                        val Semester2Table = Semester2Table()
                        Semester2Table.StudentRollno = rollno
                        Semester2Table.Tamil = tamilEdt.text.toString()
                        Semester2Table.English = englishEdt.text.toString()
                        Semester2Table.Oops = oopsEdt.text.toString()
                        Semester2Table.DiscreteMaths = discretemathsEdt.text.toString()
                        Semester2Table.OopsPractical = oopspracticalEdt.text.toString()
                        Semester2Table.InternetBasics = internetbasicsEdt.text.toString()
                        Semester2Table.Total = totalTxt.text.toString()
                        Semester2Table.Average = averageTxt.text.toString()

                        Semester2Dao?.insert(Semester2Table)
                    }

                }
            }
        }

        if (usersList?.size == 0) {

            val Semester2Table = Semester2Table()
            Semester2Table.StudentRollno = rollno
            Semester2Table.Tamil = tamilEdt.text.toString()
            Semester2Table.English = englishEdt.text.toString()
            Semester2Table.Oops = oopsEdt.text.toString()
            Semester2Table.DiscreteMaths = discretemathsEdt.text.toString()
            Semester2Table.OopsPractical = oopspracticalEdt.text.toString()
            Semester2Table.InternetBasics = internetbasicsEdt.text.toString()
            Semester2Table.Total = totalTxt.text.toString()
            Semester2Table.Average = averageTxt.text.toString()

            Semester2Dao?.insert(Semester2Table)
        }
    }

    fun Clear() {
        tamilEdt.text?.clear()
        englishEdt.text?.clear()
        oopsEdt.text?.clear()
        discretemathsEdt.text?.clear()
        oopspracticalEdt.text?.clear()
        internetbasicsEdt.text?.clear()
        totalTxt.text = ""
        averageTxt.text = ""
    }
    fun Dbvalueautoretrieve() {
        DbConnection()
        val usersList = Semester2Dao?.getAll()
        if (usersList != null) {
            for (item in usersList) {
                if (item.StudentRollno!!.toString() == rollno) {
                    tamilEdt.setText(item.Tamil)
                    englishEdt.setText(item.English)
                    oopsEdt.setText(item.Oops)
                    discretemathsEdt.setText(item.DiscreteMaths)
                    oopspracticalEdt.setText(item.OopsPractical)
                    internetbasicsEdt.setText(item.InternetBasics)
                    totalTxt.setText(item.Total)
                    averageTxt.setText(item.Average)

                }
            }
        }
    }

}
