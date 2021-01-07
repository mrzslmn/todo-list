package com.example.notes.pages.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.model.Notes
import com.example.notes.persistence.dao.NotesDaoImp
import com.example.notes.persistence.entity.NotesEntity
import com.example.notes.repository.DetailsRepository
import java.util.*

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class DetailsViewModel : ViewModel() {

    var detailsRepository: DetailsRepository

    val description: LiveData<String> get() = _description
    val _description: MutableLiveData<String> = MutableLiveData()

    val title: LiveData<String> get() = _title
    val _title: MutableLiveData<String> = MutableLiveData()

    val currentNotes: MutableLiveData<Notes> = MutableLiveData<Notes>()

    init {
        detailsRepository = DetailsRepository(NotesDaoImp())
    }

    fun updateNotes(title: String, description: String, id: Long) {
        val notesEntity: NotesEntity? = detailsRepository.findNotes(id)
        notesEntity!!.updatedDate = Date()
        notesEntity.title = title
        notesEntity.descriptions = description
        detailsRepository.updateNotes(notesEntity)
    }

    fun reinitValue(title: String?, description: String?) {
        _title.value = title
        _description.value = description
    }
}