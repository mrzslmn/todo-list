package com.example.notes.pages.newtodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.persistence.entity.NotesEntity
import com.example.notes.repository.NewTodoRepository
import com.skydoves.whatif.whatIfNotNull

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class NewTodoViewModel constructor(
    private val newTodoRepository: NewTodoRepository
) : ViewModel(){

    val description: LiveData<String> get() = _description
    val _description: MutableLiveData<String> = MutableLiveData()

    val title: LiveData<String> get() = _title
    val _title: MutableLiveData<String> = MutableLiveData()

    fun saveToObjectBox(title: String, desciption: String) {
        val notesEntity = NotesEntity()
        notesEntity.title = title
        notesEntity.descriptions = desciption
        newTodoRepository.save(notesEntity)
    }

}