package com.example.admin.casemmarks

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Observable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.admin.casemmarks.Database.AppDatabase
import com.example.admin.casemmarks.Database.Daos.LoginDao
import com.example.admin.casemmarks.Database.Entities.LoginTable
import com.example.admin.casemmarks.Staff.Staff_studentdetailsActivity
import android.arch.persistence.room.Room
import android.support.annotation.Nullable
import android.support.v7.widget.*
import android.text.TextUtils
import com.example.admin.casemmarks.Staff.Staff_studentsbiodataentryActivity
import java.util.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var staffRdo: AppCompatRadioButton
    lateinit var studentRdo: AppCompatRadioButton
    lateinit var toolbar: Toolbar
    lateinit var emailEdt: AppCompatEditText
    lateinit var passwordEdt: AppCompatEditText
    lateinit var confirmpasswordEdt: AppCompatEditText
    lateinit var signupBtn: AppCompatTextView
    lateinit var signinBtn: AppCompatTextView
    lateinit var categoryLyt: LinearLayoutCompat

    private val TAG = LoginActivity::class.java.simpleName
    lateinit var signupcategory: String
    var values = ArrayList<String>()

    private var logindao: LoginDao? = null

    override fun onClick(v: View) {
        when (v.id) {
            R.id.signupBtn -> {
                Signup()
            }
            R.id.signinBtn -> {
                Signin()
            }
        }
    }
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        staffRdo = findViewById(R.id.staffRdo) as AppCompatRadioButton
        studentRdo = findViewById(R.id.studentRdo) as AppCompatRadioButton
        toolbar = findViewById(R.id.toolbar) as Toolbar
        emailEdt = findViewById(R.id.emailEdt) as AppCompatEditText
        passwordEdt = findViewById(R.id.passwordEdt) as AppCompatEditText
        confirmpasswordEdt = findViewById(R.id.confirmpasswordEdt) as AppCompatEditText
        signupBtn = findViewById(R.id.signupBtn) as AppCompatTextView
        signinBtn = findViewById(R.id.signinBtn) as AppCompatTextView
        categoryLyt = findViewById(R.id.categoryLyt) as LinearLayoutCompat

        toolbar.setTitle("")
        setSupportActionBar(toolbar)

        signupBtn.setOnClickListener(this)
        signinBtn.setOnClickListener(this)

        }

    fun Signup() {
        if (signupBtn.text.toString() == resources.getString(R.string.signup)) {

            confirmpasswordEdt.visibility = View.VISIBLE
            categoryLyt.visibility = View.VISIBLE
            signinBtn.setText(resources.getText(R.string.signup))
            signupBtn.setText(resources.getText(R.string.signin))

        } else {
            emailEdt.text?.clear()
            passwordEdt.text?.clear()
            confirmpasswordEdt.text?.clear()
            if (staffRdo.isChecked == true) {
                staffRdo.isChecked = false
            } else {
                studentRdo.isChecked = false
            }
            confirmpasswordEdt.visibility = View.GONE
            categoryLyt.visibility = View.GONE
            signinBtn.setText(resources.getText(R.string.signin))
            signupBtn.setText(resources.getText(R.string.signup))

        }
    }
    fun Signin() {

        if (signinBtn.text.toString() == resources.getString((R.string.signup))) {

            Dbconnection()

            val usersList = logindao?.getAll()

            if (staffRdo.isChecked) {
                signupcategory = staffRdo.text.toString()
            } else {
                signupcategory = studentRdo.text.toString()
            }

            Log.e(TAG, signupcategory)

            if (emailEdt.text.toString().isEmpty()) {
                ShowToast("Email cannot be empty")
            } else if (passwordEdt.text.toString().isEmpty()) {
                ShowToast("Password cannot be empty")
            } else if (confirmpasswordEdt.text.toString().isEmpty()) {
                ShowToast("Confirm Password cannot be empty")
            } else if (confirmpasswordEdt.text.toString() != passwordEdt.text.toString()) {
                ShowToast("Both password values are mismatch")
            } else if (signupcategory.equals(null)) {
                ShowToast("Check any one of the Category")
            } else {
                Log.e(TAG, signupcategory)
                if(usersList?.size==0)
                {
                    Log.e(TAG, "chk_insert")
                    insertToDb()
                    val intent = Intent(this@LoginActivity, Staff_studentdetailsActivity::class.java)
                    intent.putExtra("username", emailEdt.text.toString())
                    intent.putExtra("category", signupcategory)
                    startActivity(intent)
                }
                if (usersList != null) {
                    for (item in usersList) {
                        if (emailEdt.text.toString() == item.Email && passwordEdt.text.toString() == item.Password) {
                            ShowToast("Already registerd")
                        } else {
                            insertToDb()
                            val intent = Intent(this@LoginActivity, Staff_studentdetailsActivity::class.java)
                            intent.putExtra("username", emailEdt.text.toString())
                            intent.putExtra("category", item.Category)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
        if (signinBtn.text.toString() == resources.getString((R.string.signin)))
        {
            Dbconnection()
            Log.e(TAG, "chk")
            val usersList = logindao?.getAll()
            if (usersList != null) {
                for (item in usersList) {
                    Log.e(
                        TAG,
                        item.id.toString() + " " + item.Email.toString() + " " + item.Password.toString() + " " + item.Category
                    )
                    if (emailEdt.text.toString().isEmpty()) {
                        ShowToast("Email id cannot be empty")
                    } else if (passwordEdt.text.toString().isEmpty()) {
                        ShowToast("Password cannot be empty")
                    } else {
                        if (emailEdt.text.toString() == item.Email && passwordEdt.text.toString() == item.Password && item.Category == "Staff") {
                            val intent = Intent(this@LoginActivity, Staff_studentdetailsActivity::class.java)
                            intent.putExtra("username", emailEdt.text.toString())
                            intent.putExtra("category", item.Category)
                            startActivity(intent)
                        }

                        else if(emailEdt.text.toString() == item.Email && passwordEdt.text.toString() == item.Password && item.Category == "Student") {
                            val intent = Intent(this@LoginActivity, Staff_studentdetailsActivity::class.java)
                            intent.putExtra("username", emailEdt.text.toString())
                            intent.putExtra("category", item.Category)
                            startActivity(intent)
                        }

                       }
                }
            }
        }
    }

    fun ShowToast(s: String) {

     Toast.makeText(this@LoginActivity, s, Toast.LENGTH_SHORT).show()
    }
    fun Dbconnection()
    {
        var db: AppDatabase? = null
        db = Room.databaseBuilder(
            this@LoginActivity,
            AppDatabase::class.java, "pocroom_database"
        ).allowMainThreadQueries().build()
        logindao = db.LoginDao()
    }

    fun insertToDb() {
        val logintable = LoginTable()
        logintable.Email = emailEdt.text.toString()
        logintable.Password = passwordEdt.text.toString()
        logintable.Category = signupcategory

        logindao?.insert(logintable)

        Log.e(TAG, emailEdt.text.toString() + " " + passwordEdt.text.toString() + " " + signupcategory)
        ShowToast("Register Successfully")
    }
}

private operator fun Int.invoke(i: Int) {

}
