package hu.itatti.calllist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = *arrayOf(Call::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun callDao():CallListDAO

    companion object{
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context?): AppDatabase{
            if(INSTANCE==null){

                INSTANCE= Room.databaseBuilder(context!!.getApplicationContext(),AppDatabase::class.java,"calllist.db").build()

            }
            return INSTANCE!!
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}