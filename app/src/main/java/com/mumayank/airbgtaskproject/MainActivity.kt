package com.mumayank.airbgtaskproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mumayank.airbgtask.AirBgTask

class MainActivity : AppCompatActivity() {

    var airBgTask = AirBgTask()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        airBgTask.onCreate(this)

        var counter = 0
        airBgTask.define({
            while (counter < 10) {
                Log.e("makmak", "$counter")
                counter++
                Thread.sleep(100L)
            }
            true
        }, {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(this, "Unable to complete", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(this, "completed, but don't know if success or failure", Toast.LENGTH_SHORT).show()
        })

        airBgTask.execute()
    }

    override fun onDestroy() {
        airBgTask.onDestroy()
        super.onDestroy()
    }
}