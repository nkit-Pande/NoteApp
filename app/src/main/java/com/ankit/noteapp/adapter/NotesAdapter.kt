package com.ankit.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ankit.noteapp.R
import com.ankit.noteapp.model.Note
import org.w3c.dom.Text
import kotlin.random.Random

class NotesAdapter(private val context:Context,val listener:NotesItemClickListener):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val noteList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = noteList[position]

        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.note_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))

        holder.note_layout.setOnClickListener {
            listener.onItemClicked(noteList[holder.adapterPosition])
        }

        holder.note_layout.setOnLongClickListener{
            listener.onLongItemClick(noteList[holder.adapterPosition],holder.note_layout)
            true
        }
    }

    fun randomColor():Int{
        val list = ArrayList<Int>()
        list.add(R.color.light_coral)
        list.add(R.color.light_gray)
        list.add(R.color.light_orchid)
        list.add(R.color.light_lilac)
        list.add(R.color.light_mint_green)
        list.add(R.color.light_goldenrod_yellow)
        list.add(R.color.light_sky_blue)
        list.add(R.color.light_pink)
        list.add(R.color.light_turquoise)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random((seed)).nextInt(list.size)
        return list[randomIndex]
    }

    fun updateList(newList : List<Note>){
        fullList.clear()
        fullList.addAll(newList)
        noteList.clear()
        noteList.addAll(fullList)


        notifyDataSetChanged()
    }

    fun filterList(search:String){
        noteList.clear()

        for(item in fullList ){
            if(item.title?.lowercase()?.contains(search.lowercase())==true ||
                    item.note?.lowercase()?.contains(search.lowercase())==true){

                noteList.add(item)
            }
        }
        notifyDataSetChanged()
    }


    inner class NoteViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val note_layout: CardView = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)

    }

    interface NotesItemClickListener{

        fun onItemClicked(note:Note)

        fun onLongItemClick(note:Note,cardView:CardView)



    }
}