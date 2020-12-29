package com.example.notes.pages.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.notes.R
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.pages.newtodo.NewTodoActivity

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class MainActivity :AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        setupListener()
    }

    private fun setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            vmNotes = mainViewModel
        }

    }

    private fun setupObserver() {

    }

    private fun setupListener() {
        binding.fabAddNotes.setOnClickListener {
            navigateToAddNotes()
        }
    }

    fun navigateToAddNotes() {
        val intent = Intent(this, NewTodoActivity::class.java)
        startActivity(intent)
    }

}