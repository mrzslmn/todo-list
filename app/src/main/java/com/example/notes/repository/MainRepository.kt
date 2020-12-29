package com.example.notes.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.notes.persistence.dao.NotesDao
import com.example.notes.persistence.entity.NotesEntity

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class MainRepository constructor(
    private val notesDao: NotesDao
) : Application(){

    fun findAllNotes(): List<NotesEntity>? {
        return notesDao.findAllNotes()
    }

    fun deleteNotes(notesEntity: NotesEntity) {
        notesDao.delete(notesEntity)
    }

}