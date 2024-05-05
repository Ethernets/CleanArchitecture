package com.example.cleararchitecture.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleararchitecture.R
import com.example.core.data.Note
import java.text.SimpleDateFormat
import java.util.Date

class NoteListAdapter(var note: ArrayList<Note>, val action: ListAction): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>(){
    private lateinit var viewHolder: RecyclerView.ViewHolder
    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val title: TextView = view.findViewById(R.id.title)
        private val content: TextView = view.findViewById(R.id.content)
        private val date: TextView = view.findViewById(R.id.date)
        private val layout: View = view.findViewById(R.id.noteLayout)
        private val worldsCount: TextView = view.findViewById(R.id.sizeText)
        fun bind(note: Note){
            title.text = note.title
            content.text = note.content
            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(note.updateTime)
            date.text = "Last updated: ${sdf.format(resultDate)}"
            worldsCount.text = "Words: ${note.wordCount}"

            layout.setOnClickListener {
                action.onClick(note.id)
            }
        }
    }

    fun updateNotes(notes: List<Note>){
        note.clear()
        note.addAll(notes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        viewHolder = NoteViewHolder(view)
        return viewHolder as NoteViewHolder
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(note[position])
    }
}