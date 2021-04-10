package hu.itatti.calllist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "callTable")
data class Call(
    @PrimaryKey(autoGenerate = true) var callId : Long?,
    @ColumnInfo(name = "phonenumber") var phoneNum: String,
    @ColumnInfo(name = "calldate") var date: String
)