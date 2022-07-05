package com.srdroid.cocktail.presentation.drink_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
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

    private val set = ConstraintSet()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.drink = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

     /*   val ratio =String.format("%d:%d", 100,100)
        set.clone(holder.c)
        set.setDimensionRatio(holder.mImgPoster.id, ratio)
        set.applyTo(holder.mConstraintLayout)*/

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}