package hu.itatti.calllist.data

import androidx.room.*


@Dao
interface CallListDAO{

    @Query("SELECT * FROM callTable ")
    fun getAllCall(): List<Call>

    @Insert
    fun addCall(call: Call):Long


}
