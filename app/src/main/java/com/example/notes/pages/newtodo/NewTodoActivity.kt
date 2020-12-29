package com.example.notes.pages.newtodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.notes.R
import com.example.notes.databinding.ActivityNewTodoBinding
import com.example.notes.pages.main.MainActivity

class NewTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTodoBinding
    val newTodoViewModel: NewTodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()

    }

    private fun setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_todo)
        binding.apply {
            lifecycleOwner= this@NewTodoActivity
            vmnewtodo = newTodoViewModel
        }
    }

    private fun setupListener() {
        binding.btnSave.setOnClickListener {
            newTodoViewModel.saveToObjectBox(
                binding.etTitle.text.toString(),
                binding.etDesc.text.toString()
            )
//            Navigate
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}