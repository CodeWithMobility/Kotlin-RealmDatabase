package com.manu.kotlinrealmdatabase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.manu.kotlinrealmdatabase.db.RealmHelper
import com.manu.kotlinrealmdatabase.model.UserInfo

/**
 * Created by manu on 12/28/2017.
 */

class AddActivity : AppCompatActivity(),View.OnClickListener {

    private var nameTV: EditText? = null
    private var ageTV: EditText? = null
    private var phoneTV: EditText? = null
    private var placeTV: EditText? = null
    private var emailTV: EditText? = null
    private var submitButton: Button? = null
    private var deleteButton: Button? = null
    private var mRealmHelper: RealmHelper? = null
    private var isAdd: Boolean? = false
    private var nameStr: String? = null
    private var ageStr: String? = null
    private var phoneStr: String? = null
    private var placeStr: String? = null
    private var emailStr: String? = null
    private var idStr: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        mRealmHelper = RealmHelper(this)
        nameTV = findViewById(R.id.nameTV)
        ageTV = findViewById(R.id.ageTV)
        phoneTV = findViewById(R.id.phoneTV)
        placeTV = findViewById(R.id.placeTV)
        emailTV = findViewById(R.id.emailTV)
        submitButton = findViewById(R.id.submitButton)
        deleteButton = findViewById(R.id.deleteButton)
        submitButton!!.setOnClickListener(this)
        deleteButton!!.setOnClickListener(this)

        isAdd = intent.getBooleanExtra("ADD", true)
        idStr = intent.getIntExtra("ID", 0)


        val userInfo = mRealmHelper!!.queryUserInfoById(idStr)
        if ((!isAdd!!) && userInfo != null) {
            nameStr = userInfo.name
            ageStr = userInfo.age.toString() + ""
            phoneStr = userInfo.phone.toString() + ""
            placeStr = userInfo.place
            emailStr = userInfo.email

            nameTV!!.setText(nameStr)
            ageTV!!.setText(ageStr)
            phoneTV!!.setText(phoneStr)
            placeTV!!.setText(placeStr)
            emailTV!!.setText(emailStr)
            deleteButton!!.visibility = View.VISIBLE
        }
    }

    private fun clearAllFields() {
        nameTV!!.setText("")
        ageTV!!.setText("")
        phoneTV!!.setText("")
        placeTV!!.setText("")
        emailTV!!.setText("")
    }

    private val isValid: Boolean
        get() {
            if (nameTV!!.text.toString().length == 0) {
                Toast.makeText(this@AddActivity, "Enter Name", Toast.LENGTH_SHORT).show()
                return false
            } else if (ageTV!!.text.toString().length == 0) {
                Toast.makeText(this@AddActivity, "Enter Age", Toast.LENGTH_SHORT).show()
                return false
            } else if (phoneTV!!.text.toString().length == 0) {
                Toast.makeText(this@AddActivity, "Enter Phone", Toast.LENGTH_SHORT).show()
                return false
            } else if (placeTV!!.text.toString().length == 0) {
                Toast.makeText(this@AddActivity, "Enter Place", Toast.LENGTH_SHORT).show()
                return false
            } else if (emailTV!!.text.toString().length == 0) {
                Toast.makeText(this@AddActivity, "Enter Email", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }

    override fun onClick(v: View) {
        if (v.id == R.id.submitButton) {
            if (isValid) {
                if (isAdd!!) {
                    mRealmHelper!!.getRealm()!!.beginTransaction()
                    val userInfo = mRealmHelper!!.getRealm()!!.createObject(UserInfo::class.java)
                    userInfo.id = (mRealmHelper!!.queryLengthofList() + System.currentTimeMillis()).toInt()
                    userInfo.name = nameTV!!.text.toString()
                    userInfo.age = Integer.valueOf(ageTV!!.text.toString())
                    userInfo.phone = Integer.valueOf(phoneTV!!.text.toString())
                    userInfo.place = placeTV!!.text.toString()
                    userInfo.email = emailTV!!.text.toString()
                    mRealmHelper!!.addUserInfo(userInfo)

                } else {
                    val userInfo = UserInfo()
                    userInfo.name = nameTV!!.text.toString()
                    userInfo.age = Integer.valueOf(ageTV!!.text.toString())
                    userInfo.phone = Integer.valueOf(phoneTV!!.text.toString())
                    userInfo.place = placeTV!!.text.toString()
                    userInfo.email = emailTV!!.text.toString()
                    mRealmHelper!!.updateUserInfo(idStr, userInfo)
                }
                clearAllFields()
            }
        } else if (v.id == R.id.deleteButton) {
            mRealmHelper!!.deleteUserInfo(idStr)
            finish()
        }
    }
}