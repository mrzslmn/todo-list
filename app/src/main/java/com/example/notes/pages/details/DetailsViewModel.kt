package com.example.notes.pages.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.model.Notes
import com.example.notes.persistence.dao.NotesDaoImp
import com.example.notes.persistence.entity.NotesEntity
import com.example.notes.repository.DetailsRepository
import com.skydoves.whatif.whatIfNotNullOrEmpty
import java.util.*

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class DetailsViewModel : ViewModel() {
    var detailsRepository: DetailsRepository
    val currentNotes: MutableLiveData<Notes> = MutableLiveData<Notes>()

    val description: LiveData<String> get() = _description
    val title: LiveData<String> get() = _title
    val error: LiveData<String> get() = _error
    val success : LiveData<Boolean> get() = _success

    private val _description = MutableLiveData<String>()
    private val _title = MutableLiveData<String>()
    private val _error = MutableLiveData<String>()
    private val _success = MutableLiveData<Boolean>()

    init {
        detailsRepository = DetailsRepository(NotesDaoImp())
    }

    fun validateNote(title: String, description: String, id: Long) {
        if (title.isEmpty()) {
            _error.postValue("Title cannot be empty")
            return
        }

        if (description.isEmpty()) {
            _error.postValue("Description cannot be empty")
            return
        }
        updateNotes(title, description, id)
    }

    fun updateNotes(title: String, description: String, id: Long) {
        val notesEntity: NotesEntity? = detailsRepository.findNotes(id)
        notesEntity!!.updatedDate = Date()
        notesEntity.title = title
        notesEntity.descriptions = description
        detailsRepository.updateNotes(notesEntity)
        _success.postValue(true)
    }

    fun reinitValue(title: String?, description: String?) {
        _title.value = title
        _description.value = description
    }
}