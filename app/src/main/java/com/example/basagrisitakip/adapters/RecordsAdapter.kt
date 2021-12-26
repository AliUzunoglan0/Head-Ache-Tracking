package com.example.basagrisitakip.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basagrisitakip.R
import com.example.basagrisitakip.models.RecordModel

class RecordsAdapter(val context: Context, val recordList: ArrayList<RecordModel>) :
    RecyclerView.Adapter<RecordsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val startTime = itemView.findViewById<TextView>(R.id.record_item_start_time)
        val endTime = itemView.findViewById<TextView>(R.id.record_item_end_time)
        val intensity = itemView.findViewById<TextView>(R.id.record_item_intensity)
        val intensityImage = itemView.findViewById<ImageView>(R.id.record_card_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.records_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = recordList[position]

        holder.startTime.text = item.startTime
        holder.endTime.text = item.endTime
        holder.intensity.text = item.painIntensity.toString()

        if (item.painIntensity in 0..17){
            holder.intensityImage.setImageResource(R.drawable.intensity1)
        } else if (item.painIntensity in 18..35){
            holder.intensityImage.setImageResource(R.drawable.intensity2)
        } else if (item.painIntensity in 36..51){
            holder.intensityImage.setImageResource(R.drawable.intensity3)
        } else if (item.painIntensity in 52..69){
            holder.intensityImage.setImageResource(R.drawable.intensity4)
        } else if (item.painIntensity in 70..87){
            holder.intensityImage.setImageResource(R.drawable.intensity5)
        } else if (item.painIntensity in 78..100){
            holder.intensityImage.setImageResource(R.drawable.intensity6)
        } else {
            holder.intensityImage.setImageResource(R.drawable.intensity1)
        }


    }

    override fun getItemCount(): Int {
        return recordList.size
    }
}