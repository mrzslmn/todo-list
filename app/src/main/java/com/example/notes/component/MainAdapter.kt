package com.example.notes.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.pages.main.MainActivity
import com.example.notes.persistence.entity.NotesEntity

/**
 * Created by M.Reza Sulaiman on 29/12/20
 * Jepara, Indonesia.
 */
class MainAdapter(mainActivity: MainActivity) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var notesEntities: List<NotesEntity> = arrayListOf()
    private var listener: MainActivity = mainActivity

    fun setNotesItem(notesEntities: List<NotesEntity>, mainActivity: MainActivity) {
        this.notesEntities = notesEntities
        this.listener = mainActivity
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
//        val binding = ItemNotesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(notesEntities[position], listener)
    }

    override fun getItemCount(): Int {
        return notesEntities.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val binding: ListMenuItemView? = DataBindingUtil.bind(itemView)
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var delete: ImageView = itemView.findViewById(R.id.iv_delete_item)
        var cardview: CardView = itemView.findViewById(R.id.card_list_notes)

        fun bindItem (notesEntity: NotesEntity, listener: MainActivity) {
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