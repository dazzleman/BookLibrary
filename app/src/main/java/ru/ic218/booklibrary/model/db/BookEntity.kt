package ru.ic218.booklibrary.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

@Entity(tableName = "books")
data class BookEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "idCategory")
    var idCategory: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String
)