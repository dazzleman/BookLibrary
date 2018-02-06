package ru.ic218.booklibrary.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable
import ru.ic218.booklibrary.model.db.CategoryEntity

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

@Dao
interface CategoryDao {

    @Insert(onConflict = REPLACE)
    fun insertCategory(category: CategoryEntity)

    @Insert(onConflict = REPLACE)
    fun insertAllCategory(vararg categories: CategoryEntity)

    @Update(onConflict = REPLACE)
    fun updateCategory(category: CategoryEntity)

    @Query("select * from category where id = :id")
    fun findCategoryById(id: Int): CategoryEntity

    @Query("SELECT * FROM category")
    fun getCategories(): Flowable<List<CategoryEntity>>

    @Query("delete from category")
    fun clearCategories()
}