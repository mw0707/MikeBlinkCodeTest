package com.example.mikeblinkcodetest.chat.presentation.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mikeblinkcodetest.R
import com.example.mikeblinkcodetest.chat.domain.model.MessageItem

class ReceivedMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textReceivedMessage: TextView =
        itemView.findViewById(R.id.text_received_message)

    private val textReceivedMessageTimestamp: TextView =
        itemView.findViewById(R.id.text_received_message_timestamp)

    fun bind(messageItem: MessageItem) {
        textReceivedMessage.text = messageItem.text
        textReceivedMessageTimestamp.text = messageItem.lastUpdated
    }
}