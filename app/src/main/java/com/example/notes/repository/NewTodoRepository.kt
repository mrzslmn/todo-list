package com.example.notes.repository

import android.app.Application
import com.example.notes.persistence.dao.NotesDao
import com.example.notes.persistence.entity.NotesEntity

/**
 * Created by M.Reza Sulaiman on 28/12/20
 * Jepara, Indonesia.
 */
class NewTodoRepository constructor(
    private val notesDao: NotesDao
) : Application()
 {

    fun save(notesEntity: NotesEntity) {
        return notesDao.save(notesEntity)
    }

}