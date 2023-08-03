package com.example.notes.pages.details

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.notes.R
import com.example.notes.databinding.ActivityDetailsBinding
import com.example.notes.model.Notes
import com.example.notes.pages.main.MainActivity
import com.example.notes.utils.CommonUtils
import timber.log.Timber

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModels()

    companion object {
        var intentId: Long = 0
        var intentTitle: String? = null
        var intentDesc: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retriveFromIntent()
        setupView()
        setupListener()
        reinitView()
        setupObserve()
    }

    fun setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.notes = Notes(intentTitle, intentDesc)
        binding.apply {
            lifecycleOwner = this@DetailsActivity
            vmDetails = detailsViewModel
        }
    }

    private fun setupObserve() {
        val notes = Observer<Notes> {
            binding.etTitle.setText(it.titleNotes)
            binding.etDesc.setText(it.decsriptionNotes)
            Timber.d("setupObserve() title: %s description: %s", binding.etTitle.text, binding.etDesc.text)
        }
        detailsViewModel.currentNotes.observe(this, notes)

        detailsViewModel.success.observe(this) {
            CommonUtils.standardToast(this,"Data has been updated")
            navigateToMain()
        }

        detailsViewModel.error.observe(this) {
            CommonUtils.standardToast(this, it)
        }

    }

    fun retriveFromIntent(){
        intentTitle = intent.getStringExtra("Title")
        intentDesc = intent.getStringExtra("Desc")
        intentId = intent.getLongExtra("Id",0)
        Timber.d("retriveFromIntent() Tittle: %s Desc: %s Id: %s", intentTitle, intentDesc, intentId)

    }

    fun reinitView() {
        detailsViewModel.reinitValue(intentTitle, intentDesc)
    }

    fun setupListener() {
        binding.btnUpdate.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDesc.text.toString()
            detailsViewModel.validateNote(
                title,
                description,
                intentId
            )

        }
    }

    private fun navigateToMain() {
        finish()
    }
}