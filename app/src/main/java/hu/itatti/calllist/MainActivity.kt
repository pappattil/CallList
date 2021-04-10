package hu.itatti.calllist

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import hu.itatti.calllist.adapter.CallAdapter
import hu.itatti.calllist.data.AppDatabase
import hu.itatti.calllist.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CallAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestNeededPermission()
        initRecyclerView()
    }




    private fun requestNeededPermission() {
        if (
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
            ) {

            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.CALL_PHONE,
                ),
                101)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            101 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    ) {
                    Toast.makeText(this@MainActivity, "All perm granted", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(this@MainActivity,
                        "Permissions NOT granted", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun initRecyclerView() {
        thread{
            val callAll = AppDatabase.getInstance(this@MainActivity).callDao().getAllCall()

            runOnUiThread {
                adapter = CallAdapter(callAll)
                val itemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)

                findViewById<RecyclerView>(R.id.recylerCalls).adapter = adapter
                findViewById<RecyclerView>(R.id.recylerCalls).addItemDecoration(itemDecoration)
                //recyclerList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            }
        }

    }




}