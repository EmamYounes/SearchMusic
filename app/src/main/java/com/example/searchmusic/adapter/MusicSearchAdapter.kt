package com.example.searchmusic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmusic.R
import com.example.searchmusic.pojo.MusicSearchModel


class MusicSearchAdapter(private var myList: MutableList<MusicSearchModel>) :
    RecyclerView.Adapter<MusicSearchAdapter.WordCountAdapterViewHolder>() {

    private var list: MutableList<MusicSearchModel>? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordCountAdapterViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.music_search_item, parent, false)
        return WordCountAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordCountAdapterViewHolder, position: Int) {
        holder.bind(list?.get(position)!!)
    }


    inner class WordCountAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var image: TextView = view.findViewById(R.id.image)
        var title: TextView = view.findViewById(R.id.title)
        var songType: TextView = view.findViewById(R.id.songType)
        var artist: TextView = view.findViewById(R.id.artist)

        fun bind(model: MusicSearchModel) {
            image.text = model.imageUrl
            title.text = model.title
            songType.text = model.songType
            artist.text = model.artist
        }
    }


    override fun getItemCount(): Int {
        return list!!.size
    }

    fun addNewItem(obj: MusicSearchModel) {
        list!!.add(obj)
        notifyItemInserted(list!!.size - 1)
    }

    fun addList(lst: List<MusicSearchModel?>) {
        list = lst as MutableList<MusicSearchModel>
        notifyDataSetChanged()
    }

    fun updateMyList(lst: List<MusicSearchModel?>) {
        myList = lst as MutableList<MusicSearchModel>
    }

    fun removeAll() {
        list!!.clear()
        notifyDataSetChanged()
    }

    fun getCurrentList(): List<MusicSearchModel> {
        return list!!
    }

    fun updateItem(pos: Int, obj: MusicSearchModel?) {
        obj?.let { list!!.removeAt(pos) }
        obj?.let { list!!.add(pos, it) }
    }

    init {
        list = myList
    }

}
