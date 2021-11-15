package com.example.trafikkskilt

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import org.opencv.android.OpenCVLoader

class StarterPageActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter_page)
    }

    fun startDriveOnClick(){
        //TODO: Make onClick method for starting drive
    }
}