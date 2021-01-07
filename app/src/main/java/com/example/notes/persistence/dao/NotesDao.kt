package com.example.notes.persistence.dao

import com.example.notes.persistence.entity.NotesEntity

/**
 * Created by M.Reza Sulaiman on 23/12/20
 * Jepara, Indonesia.
 */
interface NotesDao {
    fun save(notesEntity: NotesEntity)
    fun delete(notesEntity: NotesEntity)
    fun findNotesById(id: Long): NotesEntity?
    fun findAllNotes(): List<NotesEntity>?
    fun pagingNotes(ofset: Long): List<NotesEntity>?
}