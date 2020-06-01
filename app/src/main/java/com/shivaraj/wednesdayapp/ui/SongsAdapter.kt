package com.shivaraj.wednesdayapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shivaraj.wednesdayapp.data.local.SongsModel
import com.shivaraj.wednesdayapp.databinding.TaskItemBinding

class SongsAdapter(private val songs: ArrayList<SongsModel> ) :
    RecyclerView.Adapter<SongsAdapter.ItemHolder>() {


    companion object{

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(img : ImageView, url: String?){
            Glide.with(img.context)
                .load(url).apply(RequestOptions().circleCrop())
                .into(img)
        }
    }

    fun addTask(songModel: SongsModel) {
        songs.add(songModel)
        notifyDataSetChanged()
    }

    fun addTasks(songModel: ArrayList<SongsModel>) {
        songs.addAll(songModel)
        notifyDataSetChanged()
    }

    fun clear() {
        songs.clear()
        notifyDataSetChanged()
    }


    fun removeTask(position: Int) {
        songs.removeAt(position)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(inflater)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(songs[position], position)
    }

    fun getSize(): Int {

        return songs.size
    }


    inner class ItemHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SongsModel, position: Int) {
            binding.song = item
            binding.imageUrl = item.artworkUrl30
            binding.executePendingBindings()
        }
    }
}