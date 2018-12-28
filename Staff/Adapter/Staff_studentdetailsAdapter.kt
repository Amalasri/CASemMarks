package com.example.admin.casemmarks.Staff.Adapter

import android.content.Intent
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.casemmarks.Database.Entities.StudentBiodataTable
import com.example.admin.casemmarks.R
import com.example.admin.casemmarks.Staff.Stafftostudent_Semmarks_entryActivity
import com.example.admin.casemmarks.Student.StudentsbiodatashowActivity

class Staff_studentdetailsAdapter(
    val studentdetails: ArrayList<StudentBiodataTable>,
    val category: String
) :
    RecyclerView.Adapter<Staff_studentdetailsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.studentpersonaldetails_recyclerview_items, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.rollnoTxt.setText(studentdetails.get(position).StudentRollno)
        holder.nameTxt.setText(studentdetails.get(position).StudentName)
        val context = holder.itemView.context


        holder.rollnoTxt.setOnClickListener {
            val intent = Intent(context, StudentsbiodatashowActivity::class.java)
            intent.putExtra("studentname", studentdetails.get(position).StudentName)
            intent.putExtra("rollno", studentdetails.get(position).StudentRollno)
            intent.putExtra("dob", studentdetails.get(position).DOB)
            intent.putExtra("email", studentdetails.get(position).Email)
            intent.putExtra("gender", studentdetails.get(position).Gender)
            intent.putExtra("mobileno", studentdetails.get(position).MobileNo)
            intent.putExtra("batch", studentdetails.get(position).Batch)
            intent.putExtra("degree", studentdetails.get(position).Degree)
            intent.putExtra("bloodgroup", studentdetails.get(position).Bloodgroup)
            intent.putExtra("residentialaddress", studentdetails.get(position).ResidentialAddress)
            intent.putExtra("fathername", studentdetails.get(position).FatherName)
            intent.putExtra("mothername", studentdetails.get(position).MotherName)
            intent.putExtra("fatheroccupation", studentdetails.get(position).FatherOccupation)
            intent.putExtra("motheroccupation", studentdetails.get(position).MotherOccupation)
            intent.putExtra("fathermobileno", studentdetails.get(position).FatherMobileno)
            intent.putExtra("mothermobileno", studentdetails.get(position).MotherMobileno)
            intent.putExtra("permanentaddress", studentdetails.get(position).PermanentAddress)
            intent.putExtra("studentcategory", studentdetails.get(position).StudentCategory)
            context.startActivity(intent)
        }

        holder.semmarkTxt.setOnClickListener {
            val intent = Intent(context, Stafftostudent_Semmarks_entryActivity::class.java)
            intent.putExtra("rollno", holder.rollnoTxt.text.toString())
            intent.putExtra("category",category)
            Log.e("Tag", holder.rollnoTxt.text.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return studentdetails.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context = itemView.context

        val rollnoTxt = view.findViewById(R.id.rollnoTxt) as AppCompatTextView
        val nameTxt = view.findViewById(R.id.nameTxt) as AppCompatTextView
        val semmarkTxt = view.findViewById(R.id.semmarkTxt) as AppCompatTextView

    }
}



