package com.omaresli.gallery.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.omaresli.gallery.viewmodels.RowViewModel

class CatalogAdapter(val viewModelId: Int) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: RowViewModel) {
            binding.setVariable(viewModelId, itemViewModel)
            binding.executePendingBindings()
        }
    }

    private val viewModels: MutableList<RowViewModel> = mutableListOf()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bind(viewModels[position])
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewModels[position].resourceLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        val itemBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(itemBinding)
    }

    fun replaceItems(viewModels: List<RowViewModel>) {
        this.viewModels.clear()
        this.viewModels.addAll(viewModels)
        notifyDataSetChanged()
    }

    fun addItems(viewModels: List<RowViewModel>) {
        val positionInserted = this.viewModels.size
        this.viewModels.addAll(viewModels)
        notifyItemRangeInserted(positionInserted, this.viewModels.size)
    }
}