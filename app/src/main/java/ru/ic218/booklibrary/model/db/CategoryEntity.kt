package ru.ic218.booklibrary.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

@Entity(tableName = "category")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String
)