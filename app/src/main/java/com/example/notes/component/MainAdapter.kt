package com.example.notes.component

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.pages.main.MainActivity
import com.example.notes.persistence.entity.NotesEntity
import timber.log.Timber

/**
 * Created by M.Reza Sulaiman on 29/12/20
 * Jepara, Indonesia.
 */
class MainAdapter(mainActivity: MainActivity) : PagingDataAdapter<NotesEntity, MainAdapter.ViewHolder>(
    NOTES_COMPARATOR) {

    private var listener: MainActivity = mainActivity

    companion object {
        private val NOTES_COMPARATOR = object : DiffUtil.ItemCallback<NotesEntity>() {
            override fun areItemsTheSame(oldItem: NotesEntity, newItem: NotesEntity): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: NotesEntity, newItem: NotesEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            Timber.d("onBindViewHolder() position: %s", position)
            holder.bindItem(it, listener)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.tv_title)
        private var delete: ImageView = itemView.findViewById(R.id.iv_delete_item)
        private var cardview: CardView = itemView.findViewById(R.id.card_list_notes)

        fun bindItem(notesEntity: NotesEntity, listener: MainActivity) {
            title.text = notesEntity.title

            cardview.setOnClickListener {
                listener.onDetailClicked(notesEntity)
            }

            delete.setOnClickListener {
                listener.onDeleteClicked(notesEntity)
            }
        }
    }
}