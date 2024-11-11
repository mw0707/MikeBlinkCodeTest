package com.example.mikeblinkcodetest.chatslist.presentation.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mikeblinkcodetest.R
import com.example.mikeblinkcodetest.chatslist.domain.model.ChatItem

class ChatListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.name)
    private val lastUpdated: TextView = itemView.findViewById(R.id.last_updated)

    fun bind(chatItem: ChatItem) {
        name.text = chatItem.name
        val lastUpdatedText =
            itemView.context.getString(R.string.last_updated) + chatItem.lastUpdated
        lastUpdated.text = lastUpdatedText
    }
}