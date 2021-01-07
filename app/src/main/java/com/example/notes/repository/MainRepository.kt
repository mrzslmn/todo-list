package com.example.notes.repository

import android.app.Application
import com.example.notes.persistence.dao.NotesDao
import com.example.notes.persistence.entity.NotesEntity

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class MainRepository constructor(
    private val notesDao: NotesDao
) : Application(){

    companion object {
        private const val NOTES_PAGE_SIZE = 10
    }

    fun findAllNotes(): List<NotesEntity>? {
        return notesDao.findAllNotes()
    }

    fun deleteNotes(notesEntity: NotesEntity) {
        notesDao.delete(notesEntity)
    }

    fun pagingNotes(ofset: Long): List<NotesEntity>?{
        return notesDao.pagingNotes(ofset)
    }


}