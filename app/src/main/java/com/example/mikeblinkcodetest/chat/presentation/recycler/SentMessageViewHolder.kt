package com.example.mikeblinkcodetest.chat.presentation.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mikeblinkcodetest.R
import com.example.mikeblinkcodetest.chat.domain.model.MessageItem

class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textSentMessage: TextView =
        itemView.findViewById(R.id.text_sent_message)

    private val textSentMessageTimestamp: TextView =
        itemView.findViewById(R.id.text_sent_message_timestamp)

    fun bind(messageItem: MessageItem) {
        textSentMessage.text = messageItem.text
        textSentMessageTimestamp.text = messageItem.lastUpdated
    }
}