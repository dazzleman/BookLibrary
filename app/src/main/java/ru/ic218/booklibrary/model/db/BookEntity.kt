package ru.ic218.booklibrary.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

@Entity(tableName = "books",
        foreignKeys = arrayOf(ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idCategory"))))
data class BookEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "idCategory")
    var idCategory: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String
)