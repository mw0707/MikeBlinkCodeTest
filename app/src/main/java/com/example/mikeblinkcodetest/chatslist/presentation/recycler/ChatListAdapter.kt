package com.example.mikeblinkcodetest.chatslist.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mikeblinkcodetest.R
import com.example.mikeblinkcodetest.chatslist.domain.model.ChatItem

class ChatListAdapter(
    private val onChatClickListener: OnChatClickListener
) : RecyclerView.Adapter<ChatListViewHolder>() {
    private val items = mutableListOf<ChatItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ChatListViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { onChatClickListener.onChatClick(items[position]) }
    }

    fun setItems(chatItems: List<ChatItem>) {
        items.clear()
        items.addAll(chatItems)
        //TODO: Bad practice, should be with DiffUtil!
        notifyDataSetChanged()
    }
}

interface OnChatClickListener {
    fun onChatClick(item: ChatItem)
}