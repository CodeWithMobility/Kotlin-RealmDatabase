package com.manu.kotlinrealmdatabase

/**
 * Created by manu on 12/28/2017.
 */

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.manu.kotlinrealmdatabase.model.UserInfo

/**
 * Created by Manu on 11/6/2017.
 */

class UserInfoAdapter(private val context: Context, private val mValues: List<UserInfo>, private val type: Int) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.nameTV.text = mValues[position].name
        holder.ageTV.text = mValues[position].age.toString() + ""
        holder.phoneTV.text = mValues[position].phone.toString() + ""

        holder.itemLayout.setOnClickListener {
            val intent = Intent(context, AddActivity::class.java)
            intent.putExtra("ADD", false)
            intent.putExtra("ID", mValues[position].id)
            //                intent.putExtra("NAME",mValues.get(position).getName());
            //                intent.putExtra("AGE",mValues.get(position).getAge()+"");
            //                intent.putExtra("PHONE",mValues.get(position).getPhone()+"");
            //                intent.putExtra("PLACE",mValues.get(position).getPlace());
            //                intent.putExtra("EMAIL",mValues.get(position).getEmail());
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mValues.size
    }
}