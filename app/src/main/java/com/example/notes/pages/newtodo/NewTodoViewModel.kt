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
    val title: LiveData<String> get() = _title
    val error: LiveData<String> get() = _error
    val success : LiveData<Boolean> get() = _success

    private val _description = MutableLiveData<String>()
    private val _title = MutableLiveData<String>()
    private val _error = MutableLiveData<String>()
    private val _success = MutableLiveData<Boolean>()

    init {
        newTodoRepository = NewTodoRepository(NotesDaoImp())
    }

    fun validateNote(title: String, description: String) {
        if (title.isEmpty()) {
            _error.postValue("Title cannot be empty")
            return
        }

        if (description.isEmpty()) {
            _error.postValue("Description cannot be empty")
            return
        }

        saveToObjectBox(title, description)
    }


    private fun saveToObjectBox(title: String, desciption: String) {
        val notesEntity = NotesEntity()
        notesEntity.title = title
        notesEntity.descriptions = desciption
        notesEntity.createdDate = Date()
        notesEntity.updatedDate = Date()
        notesEntity.createdBy = "Reza"
        newTodoRepository.save(notesEntity)
        _success.postValue(true)
    }
}