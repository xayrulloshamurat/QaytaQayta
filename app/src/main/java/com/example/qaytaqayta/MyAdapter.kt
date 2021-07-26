package com.example.qaytaqayta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*


class MyAdapter: RecyclerView.Adapter<MyAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun populateModel(user: User) {
            itemView.ati.text = user.username
            itemView.familyasi.text = user.surname
            itemView.moreItem.setOnClickListener {
                onMoreClicker.invoke(user, it)
            }
        }
    }
    fun removeItem(user: User){
        val list = models.toMutableList()
        val index = models.indexOf(user)
        list.removeAt(index)
        models= list
        notifyItemRemoved(index)
        notifyItemRangeChanged(0, models.size)


    }
    var models : List<User> = mutableListOf()
        set(value) {
            field=value
            notifyDataSetChanged()
        }
    var onMoreClicker : (user:User, view: View)-> Unit = { user, view->

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }
}
