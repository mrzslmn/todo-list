package com.example.notes.pages.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.notes.pagingsource.NotesPagingSource
import com.example.notes.persistence.dao.NotesDaoImp
import com.example.notes.persistence.entity.NotesEntity
import com.example.notes.repository.MainRepository
import kotlinx.coroutines.launch

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class MainViewModel : ViewModel(){
    var mainRepository: MainRepository
    val listItems: LiveData<List<NotesEntity>> get() = _listItems
    val _listItems: MutableLiveData<List<NotesEntity>> = MutableLiveData()


    companion object {
        private const val NOTES_PAGE_SIZE = 5
    }


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

//    fun pagingNotes(ofset: Long) {
//        val notes: List<NotesEntity>? = mainRepository.pagingNotes(ofset)
//        _listItems.value = notes
//    }


    private fun fetchNotes() {
        viewModelScope.launch {

        }
    }

//    fun getNotes(): kotlinx.coroutines.flow.Flow<PagingData<NotesEntity>> {
//        Timber.d("getNotes() load notes")
//        return Pager(
//            config = PagingConfig(
//                pageSize = NOTES_PAGE_SIZE,
//                enablePlaceholders = true
//            ),
//            pagingSourceFactory = { NotesPagingSource(mainRepository)}
//        ).flow.cachedIn(viewModelScope)
//    }

    val listNotes = Pager(
        config = PagingConfig(
            pageSize = NOTES_PAGE_SIZE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { NotesPagingSource(mainRepository)}
    ).flow.cachedIn(viewModelScope)






}