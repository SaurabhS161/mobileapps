package com.example.petsapp

import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petsapp.models.PetModelClass
import com.example.petsapp.models.PetsList
import com.squareup.picasso.Picasso

class PetViewHolder(view: View, listener: RecyclerViewAdapter.onItemClickListener): RecyclerView.ViewHolder(view) {
    var image: ImageView = view.findViewById(R.id.image)
    var title: TextView = view.findViewById(R.id.txt_title)
    var date: TextView = view.findViewById(R.id.txt_date)

    init {
        view.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}

class RecyclerViewAdapter(private var pets: List<PetModelClass>): RecyclerView.Adapter<PetViewHolder>() {

    private lateinit var mListener :onItemClickListener

    interface onItemClickListener {

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    fun loadData(newPetList: List<PetModelClass>) {
        pets = newPetList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_list_row, parent, false)
        return PetViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = pets.get(position)

        holder.title.text = pet.title
        holder.date.text = pet.date_added
        Picasso.get()
            .load(pet.image_url)
            .placeholder(R.drawable.placeholder)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return pets.size
    }
}