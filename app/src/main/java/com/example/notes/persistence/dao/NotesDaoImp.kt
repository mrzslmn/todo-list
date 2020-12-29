package com.example.notes.persistence.dao

import com.example.notes.initializer.ObjectBox
import com.example.notes.persistence.entity.NotesEntity
import com.example.notes.persistence.entity.NotesEntity_
import com.skydoves.whatif.whatIfNotNull
import io.objectbox.Box
import io.objectbox.android.ObjectBoxLiveData

/**
 * Created by M.Reza Sulaiman on 23/12/20
 * Jepara, Indonesia.
 */
class NotesDaoImp : NotesDao {
    lateinit var notesEntityBox: Box<NotesEntity>

    override fun save(notesEntity: NotesEntity) {
        notesEntityBox = ObjectBox.boxStore.boxFor(NotesEntity::class.java)
        notesEntityBox.put(notesEntity)
    }

    override fun delete(notesEntity: NotesEntity) {
        notesEntityBox = ObjectBox.boxStore.boxFor(NotesEntity::class.java)
        notesEntityBox.remove(notesEntity)
    }

    override fun findNotesById (id: Long): NotesEntity? {

        val notesEntities: List<NotesEntity> = notesEntityBox.query().equal(NotesEntity_.id, id).build().find()
        notesEntities.whatIfNotNull (whatIf = { return notesEntities[0] })
        return null

    }

    override fun findAllNotes() : List<NotesEntity>? {
        val notesEntities: List<NotesEntity> = notesEntityBox.query().notNull(NotesEntity_.id).build().find()
        notesEntities.whatIfNotNull (whatIf = { return notesEntities })
        return null
    }

}