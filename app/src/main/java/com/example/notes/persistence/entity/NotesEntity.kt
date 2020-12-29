package com.example.notes.persistence.entity

import io.objectbox.annotation.Entity

/**
 * Created by M.Reza Sulaiman on 21/12/20
 * Jepara, Indonesia.
 */

@Entity
class NotesEntity : BaseEntity {
    var title : String? = null
    var descriptions : String? = null

    constructor()
}