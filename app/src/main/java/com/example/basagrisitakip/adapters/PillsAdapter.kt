package com.example.basagrisitakip.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basagrisitakip.R
import com.example.basagrisitakip.helpers.CheckboxListListener
import com.example.basagrisitakip.models.PillsModel

class PillsAdapter(val context: Context, val list: ArrayList<PillsModel>, val checkboxListListener: CheckboxListListener) :
    RecyclerView.Adapter<PillsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pillsName = itemView.findViewById<TextView>(R.id.pills_item_name)
        val pillsCheckbox = itemView.findViewById<CheckBox>(R.id.pills_item_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pills_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listITem = list[position]

        holder.pillsName.text = listITem.name
        holder.pillsCheckbox.isChecked = listITem.isChecked

        holder.itemView.setOnClickListener {
            if (!listITem.isChecked){
                checkboxListListener.pillsCheckboxListener(position,true)
                holder.pillsCheckbox.isChecked = true
            } else{
                checkboxListListener.pillsCheckboxListener(position,false)
                holder.pillsCheckbox.isChecked = false
            }

        }
        holder.pillsCheckbox.setOnClickListener {
            if (!listITem.isChecked){
                checkboxListListener.pillsCheckboxListener(position,true)
                holder.pillsCheckbox.isChecked = true
            } else{
                checkboxListListener.pillsCheckboxListener(position,false)
                holder.pillsCheckbox.isChecked = false
            }

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}