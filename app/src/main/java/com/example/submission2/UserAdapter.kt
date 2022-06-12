package com.example.submission2

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission2.DetailUserActivity.Companion.DETAIL_USER
import com.example.submission2.databinding.ItemRowUserBinding

class UserAdapter(private val listUsers: List<ItemUser>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(users: ItemUser) {
            binding.apply {
                Glide.with(itemView)
                    .load(users.avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.github)
                    .error(R.drawable.github)
                    .into(imgAvatar)
                tvItemUsername.text = users.username
                itemView.setOnClickListener {
                    val intentToDetailAct = Intent(itemView.context, DetailUserActivity::class.java)
                    intentToDetailAct.putExtra(DETAIL_USER, users.username)
                    itemView.context.startActivity(intentToDetailAct)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRowUserBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size
}