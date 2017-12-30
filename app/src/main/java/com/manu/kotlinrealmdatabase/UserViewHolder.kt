package com.manu.kotlinrealmdatabase

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by manu on 12/28/2017.
 */

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTV: TextView
    val ageTV: TextView
    val phoneTV: TextView
    val itemLayout: LinearLayout


    init {
        nameTV = view.findViewById(R.id.nameTV)
        ageTV = view.findViewById(R.id.ageTV)
        phoneTV = view.findViewById(R.id.phoneTV)
        itemLayout = view.findViewById(R.id.ItemLayout)

    }
}