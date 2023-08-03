package com.example.notes.pagingsource

import androidx.paging.PagingSource
import com.example.notes.persistence.entity.NotesEntity
import com.example.notes.repository.MainRepository
import kotlinx.coroutines.delay
import timber.log.Timber
import java.io.IOException

/**
 * Created by M.Reza Sulaiman on 04/01/21
 * Jepara, Indonesia.
 */

private const val NOTES_STARTING_PAGE_INDEX = 0

class NotesPagingSource (
    private val mainRepository: MainRepository)
    : PagingSource<Int, NotesEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NotesEntity> {
        val position = params.key?: NOTES_STARTING_PAGE_INDEX
        Timber.d("load() position: %s", position)
        return try {
            val listNotes = mutableListOf<NotesEntity>()
            val list = mainRepository.pagingNotes(position.toLong())
            list?.let { listNotes.addAll(it) }
            Timber.d("load() listNotes size: %s", listNotes.size)
            delay(500)
            LoadResult.Page(
                data = listNotes,
                prevKey = if (position == NOTES_STARTING_PAGE_INDEX) null else position,
                nextKey = if (listNotes.isEmpty()) null else position + 2
            )
        }catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}