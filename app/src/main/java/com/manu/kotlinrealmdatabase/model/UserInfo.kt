package com.manu.kotlinrealmdatabase.model

/**
 * Created by manu on 12/28/2017.
 */

import io.realm.RealmObject



open class UserInfo : RealmObject() {
    // @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var age: Int = 0
    var phone: Int = 0
    var place: String? = null
    var email: String? = null
}