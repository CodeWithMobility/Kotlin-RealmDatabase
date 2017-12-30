package com.manu.kotlinrealmdatabase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import com.manu.kotlinrealmdatabase.db.RealmHelper
import com.manu.kotlinrealmdatabase.model.UserInfo

/**
 * Created by manu on 12/28/2017.
 */

class UserListActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    internal lateinit var realmHelper: RealmHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        realmHelper = RealmHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = linearLayoutManager

        if (realmHelper.queryAllUsers().size > 0) {
            val adapter = UserInfoAdapter(this, realmHelper.queryAllUsers(), 0)
            recyclerView!!.adapter = adapter
        }
    }
}