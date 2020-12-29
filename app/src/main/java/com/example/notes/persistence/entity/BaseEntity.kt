package com.example.notes.persistence.entity

import io.objectbox.annotation.BaseEntity
import io.objectbox.annotation.Id
import java.util.*

/**
 * Created by M.Reza Sulaiman on 21/12/20
 * Jepara, Indonesia.
 */

@BaseEntity
abstract class BaseEntity {
    @Id
    var id: Long = 0
    var createdBy: String? = null
    var createdDate: Date? = null
    var updatedBy: String? = null
    var updatedDate: Date? = null

    constructor()
}