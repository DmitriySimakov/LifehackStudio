package com.dmitrysimakov.lifehackstudio.ui.companies_list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.dmitrysimakov.lifehackstudio.BASE_URL
import com.dmitrysimakov.lifehackstudio.R
import com.dmitrysimakov.lifehackstudio.data.CompanyItem
import com.dmitrysimakov.lifehackstudio.databinding.CompanyItemBinding
import com.dmitrysimakov.lifehackstudio.databinding.FragmentCompanyInfoBinding

class CompaniesListAdapter(
    private val onClick: (CompanyItem) -> Unit
) : ListAdapter<CompanyItem, CompaniesListAdapter.ViewHolder>(CompanyItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(CompanyItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            Glide.with(root).load(BASE_URL + item.img).into(img)
            name.text = item.name
            root.setOnClickListener { onClick(item) }
        }
    }

    class ViewHolder(val binding: CompanyItemBinding) : RecyclerView.ViewHolder(binding.root)

    class CompanyItemDiffCallback : DiffUtil.ItemCallback<CompanyItem>() {
        override fun areItemsTheSame(oldItem: CompanyItem, newItem: CompanyItem): Boolean{
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: CompanyItem, newItem: CompanyItem): Boolean {
            return oldItem == newItem
        }
    }
}