package com.sugarmaniac.testapplication.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sugarmaniac.testapplication.BR
import com.sugarmaniac.testapplication.R

class BindableRecyclerViewAdapter : RecyclerView.Adapter<BindableViewHolder>() {

    var deviceItem: List<Device> = emptyList()
    lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener{
        fun onItemClick(device: Device)
        fun onDeleteClick(position: Int)
        fun onEditClick(device: Device)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_item,
            parent,
            false)
        return BindableViewHolder(binding)
    }

    override fun getItemCount(): Int = deviceItem.size

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.bind(deviceItem[position])
        holder.itemView.setOnClickListener { itemClickListener.onItemClick(device = deviceItem[position]) }
        holder.itemView.setOnLongClickListener {
            itemClickListener.onDeleteClick(position = position)
            true }
        holder.itemView.findViewById<ImageView>(R.id.edit_device).setOnClickListener { itemClickListener.onEditClick(device = deviceItem[position]) }
    }

    fun updateItems(items: List<Device>?) {
        deviceItem = items ?: emptyList()
        notifyDataSetChanged()
    }

    fun setOnClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
}

class BindableViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(device: Device) {
        binding.setVariable(BR.device, device)
    }
}