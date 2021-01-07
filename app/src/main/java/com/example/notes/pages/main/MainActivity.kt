package com.example.notes.pages.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.component.MainAdapter
import com.example.notes.component.PhotosLoadStateAdapter
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.pages.details.DetailsActivity
import com.example.notes.pages.newtodo.NewTodoActivity
import com.example.notes.persistence.entity.NotesEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            vmNotes = mainViewModel
        }
        setupRecyleView()

    }

    private fun setupObserver() {
        lifecycleScope.launch {
            mainViewModel.listNotes.collect {
                mainAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            mainAdapter.loadStateFlow.collect {
                Timber.d("setupObserver() loadState %s", it)
                binding.includeMainContent.prgressBar.isVisible = it.append is LoadState.Loading

            }
        }

    }

    private fun setupListener() {
        binding.fabAddNotes.setOnClickListener {
            navigateToAddNotes()
        }
    }

    private fun setupRecyleView() {
        val recyclerView = binding.includeMainContent.rvNotesList
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        mainAdapter = MainAdapter(this)
        recyclerView.adapter = mainAdapter

        mainAdapter.withLoadStateFooter(
         footer = PhotosLoadStateAdapter { mainAdapter.retry()}
        )
        Timber.d("setupRecyleView() footer")

    }

    fun navigateToAddNotes() {
        val intent = Intent(this, NewTodoActivity::class.java)
        startActivity(intent)
    }


    fun onDeleteClicked(notesEntity: NotesEntity) {
        mainViewModel.deleteNotes(notesEntity)
        startActivity(intent)
        finish()

    }

    fun onDetailClicked(notesEntity: NotesEntity) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("Title", notesEntity.title)
        intent.putExtra("Desc", notesEntity.descriptions)
        intent.putExtra("Id", notesEntity.id)
        startActivity(intent)
        finish()
    }




}