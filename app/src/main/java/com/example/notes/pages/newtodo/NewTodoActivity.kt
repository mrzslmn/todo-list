package com.example.notes.pages.newtodo

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.notes.R
import com.example.notes.databinding.ActivityNewTodoBinding
import com.example.notes.pages.main.MainActivity
import com.example.notes.utils.CommonUtils

class NewTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTodoBinding
    private val newTodoViewModel: NewTodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_todo)
        binding.apply {
            lifecycleOwner= this@NewTodoActivity
            vmnewtodo = newTodoViewModel
        }
    }

    private fun setupObserver() {
        newTodoViewModel.success.observe(this) {
            CommonUtils.standardToast(this,"Data has been created")
            navigateToMain()
        }

        newTodoViewModel.error.observe(this) {
            CommonUtils.standardToast(this, it)
        }
    }

    private fun setupListener() {
        binding.btnSave.setOnClickListener {
            newTodoViewModel.validateNote(
                binding.etTitle.text.toString(),
                binding.etDesc.text.toString()
            )
        }
    }

    private fun navigateToMain() {
        finish()
    }
}