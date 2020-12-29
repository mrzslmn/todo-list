package com.example.notes.pages.main

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.component.MainAdapter
import com.example.notes.pages.newtodo.NewTodoActivity
import com.example.notes.persistence.dao.NotesDaoImp
import com.example.notes.persistence.entity.NotesEntity
import com.example.notes.repository.MainRepository
import com.example.notes.repository.NewTodoRepository

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class MainViewModel : ViewModel(){
    var mainRepository: MainRepository


    val listItems: LiveData<List<NotesEntity>> get() = _listItems
    val _listItems: MutableLiveData<List<NotesEntity>> = MutableLiveData()

//    val listNotes:  LiveData<List<NotesEntity>>


    init {
        mainRepository = MainRepository(NotesDaoImp())

    }

    fun findAllnotes(){
       val notes: List<NotesEntity>? = mainRepository.findAllNotes()
        _listItems.value = notes
    }

    fun deleteNotes(notesEntity: NotesEntity) {
        mainRepository.deleteNotes(notesEntity)
    }


}