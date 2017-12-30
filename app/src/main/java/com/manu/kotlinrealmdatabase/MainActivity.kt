package com.manu.kotlinrealmdatabase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class MainActivity : AppCompatActivity(), OnClickListener {

    private var addButton: Button? = null
    private var showButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)
        addButton!!.setOnClickListener(this)
        showButton = findViewById(R.id.showButton)
        showButton!!.setOnClickListener(this)


    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.addButton -> {
                val intent = Intent(this, AddActivity::class.java)
                intent.putExtra("ADD", true);
                startActivity(intent)
            }
            R.id.showButton -> {
              val intent = Intent(this, UserListActivity::class.java)
              startActivity(intent)
            }
        }
    }
}
