package com.felix.mentalfirst.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.felix.mentalfirst.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}