package com.example.reviewerpicture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reviewerpicture.R
import com.example.reviewerpicture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding =
        setContentView(R.layout.activity_main)
    }
}