package com.asterisk.runtracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.runtracker.databinding.ItemRunBinding
import com.asterisk.runtracker.db.Run
import com.asterisk.runtracker.utils.TrackingUtility
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter : RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    inner class RunViewHolder(private val binding: ItemRunBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(run: Run) {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = run.timeInMillis
            }

            val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            binding.apply {
                Glide.with(itemView)
                    .load(run.img)
                    .into(ivRunImage)

                tvDate.text = dateFormat.format(calendar.time)

                val avgSpeed = "${run.avgSpeedInKMH}km/h"
                tvAvgSpeed.text = avgSpeed

                val distanceInKm = "${run.distanceInMeters / 1000f}km"
                tvDistance.text = distanceInKm

                tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

                val caloriesBurned = "${run.caloriesBurned}cal"
                tvCalories.text = caloriesBurned
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        val binding = ItemRunBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )

        return RunViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
        if (run != null) {
            holder.bind(run)
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    val diffCallback = object : DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Run>) = differ.submitList(list)
}