package com.manu.kotlinrealmdatabase.db

import android.content.Context
import com.manu.kotlinrealmdatabase.model.UserInfo
import io.realm.Realm

/**
 * Created by manu on 12/28/2017.
 */
class RealmHelper {
    val DB_NAME = "myDatabase.realm"
    private var mRealm: Realm? = null

    constructor(context: Context) {
        mRealm = Realm.getDefaultInstance()
    }

    /**
     * add Data
     */
    fun addUserInfo(userInfo: UserInfo) {

        mRealm!!.copyToRealm(userInfo)
        mRealm!!.commitTransaction()

    }

    /**
     * query getWholeData
     */
    fun queryAllUsers(): List<UserInfo> {
        var realmResults = mRealm!!.where(UserInfo::class.java).findAll()
        realmResults = realmResults.sort("id")
        return mRealm!!.copyFromRealm(realmResults)
    }

    fun queryLengthofList(): Int {
        var realmResults = mRealm!!.where(UserInfo::class.java).findAll()
        realmResults = realmResults.sort("id")

        return mRealm!!.copyFromRealm(realmResults).size
    }

    /**
     * query userinfo by id
     */
    fun queryUserInfoById(id: Int): UserInfo? {
        if(mRealm==null){
            return null
        }
        return mRealm!!.where(UserInfo::class.java).equalTo("id", id).findFirst()
    }

    /**
     * query userinfo by date of birth
     */
    fun queryDobByAge(age: Int): List<UserInfo> {
        val userInfos = mRealm!!.where(UserInfo::class.java).equalTo("age", age).findAll()
        return mRealm!!.copyFromRealm(userInfos)
    }

    fun isUserInfoExist(id: String): Boolean {
        val userInfo = mRealm!!.where(UserInfo::class.java).equalTo("id", id).findFirst()
        return userInfo != null
    }


    /**
     * delete userinfo by Id
     */
    fun deleteUserInfo(id: Int) {
        val userInfo = mRealm!!.where(UserInfo::class.java).equalTo("id", id).findFirst()
        mRealm!!.beginTransaction()
        userInfo.deleteFromRealm()
        mRealm!!.commitTransaction()

    }

    /**
     * update UserInfo by ID
     */
    fun updateUserInfo(id: Int, userInfoModel: UserInfo) {
        val userInfo = mRealm!!.where(UserInfo::class.java).equalTo("id", id).findFirst()
        mRealm!!.beginTransaction()
        userInfo.name = userInfoModel.name
        userInfo.age = userInfoModel.age
        userInfo.phone = userInfoModel.phone
        userInfo.place = userInfoModel.place
        userInfo.email = userInfoModel.email
        mRealm!!.commitTransaction()
    }


    fun getRealm(): Realm? {
        return mRealm
    }

    fun close() {
        if (mRealm != null) {
            mRealm!!.close()
        }
    }
}