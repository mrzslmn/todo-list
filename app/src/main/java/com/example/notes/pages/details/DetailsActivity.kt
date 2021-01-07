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
import timber.log.Timber

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    val detailsViewModel: DetailsViewModel by viewModels()

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

    fun setupObserve() {
        val notes = Observer<Notes> {
            binding.etTitle.setText(it.titleNotes)
            binding.etDesc.setText(it.decsriptionNotes)
            Timber.d("setupObserve() title: %s description: %s", binding.etTitle.text, binding.etDesc.text)
        }
        detailsViewModel.currentNotes.observe(this, notes)

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
            detailsViewModel.updateNotes(
                binding.etTitle.text.toString(),
                binding.etDesc.text.toString(),
                intentId
            )
            navigateToMain()
        }
    }

    fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}