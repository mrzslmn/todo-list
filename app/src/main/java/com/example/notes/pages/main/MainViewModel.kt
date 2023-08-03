package com.example.notes.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.notes.pagingsource.NotesPagingSource
import com.example.notes.persistence.dao.NotesDaoImp
import com.example.notes.persistence.entity.NotesEntity
import com.example.notes.repository.MainRepository

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class MainViewModel : ViewModel(){
    var mainRepository: MainRepository = MainRepository(NotesDaoImp())

    companion object {
        private const val NOTES_PAGE_SIZE = 5
    }

    fun deleteNotes(notesEntity: NotesEntity) {
        mainRepository.deleteNotes(notesEntity)
    }

    val listNotes = Pager(
        config = PagingConfig(
            pageSize = NOTES_PAGE_SIZE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { NotesPagingSource(mainRepository)}
    ).flow.cachedIn(viewModelScope)

}