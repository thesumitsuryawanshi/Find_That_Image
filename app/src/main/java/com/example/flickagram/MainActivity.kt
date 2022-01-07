package com.example.flickagram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flickagram.adapters.ClickedImage
import com.example.flickagram.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ClickedImage {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
