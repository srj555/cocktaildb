package com.srdroid.cocktail.presentation.drink_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srdroid.cocktail.databinding.ViewHolderSearchListBinding
import com.srdroid.cocktail.domain.model.Drink

class DrinkSearchAdapter : RecyclerView.Adapter<DrinkSearchAdapter.MyViewHolder>() {


    private var listener :((Drink)->Unit)?=null

    var list = mutableListOf<Drink>()

    fun setContentList(list: MutableList<Drink>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ViewHolderSearchListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            ViewHolderSearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(Drink)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.drink = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}