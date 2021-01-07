package com.example.notes.pages.newtodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.persistence.dao.NotesDaoImp
import com.example.notes.persistence.entity.NotesEntity
import com.example.notes.repository.NewTodoRepository
import java.util.*

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class NewTodoViewModel : ViewModel(){

    var newTodoRepository: NewTodoRepository

    val description: LiveData<String> get() = _description
    val _description: MutableLiveData<String> = MutableLiveData()

    val title: LiveData<String> get() = _title
    val _title: MutableLiveData<String> = MutableLiveData()

    init {
        newTodoRepository = NewTodoRepository(NotesDaoImp())
    }

    fun saveToObjectBox(title: String, desciption: String) {
        val notesEntity = NotesEntity()
        notesEntity.title = title
        notesEntity.descriptions = desciption
        notesEntity.createdDate = Date()
        notesEntity.updatedDate = Date()
        notesEntity.createdBy = "Reza"
        newTodoRepository.save(notesEntity)
    }
}