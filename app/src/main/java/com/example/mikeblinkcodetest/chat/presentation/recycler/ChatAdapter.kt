package com.example.mikeblinkcodetest.chat.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mikeblinkcodetest.R
import com.example.mikeblinkcodetest.base.utils.Constants.MY_ID
import com.example.mikeblinkcodetest.chat.domain.model.MessageItem

const val VIEW_TYPE_MESSAGE_SENT = 1
const val VIEW_TYPE_MESSAGE_RECEIVED = 2

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<MessageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MESSAGE_SENT -> {
                val view = inflater.inflate(R.layout.sent_message_item, parent, false)
                SentMessageViewHolder(view)
            }

            VIEW_TYPE_MESSAGE_RECEIVED -> {
                val view = inflater.inflate(R.layout.received_message_item, parent, false)
                ReceivedMessageViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (items[position].senderId == MY_ID) {
            (holder as SentMessageViewHolder).bind(item)
        } else {
            (holder as ReceivedMessageViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].senderId == MY_ID) {
            VIEW_TYPE_MESSAGE_SENT
        } else {
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    fun addItems(messageItems: List<MessageItem>) {
        items.clear()
        items.addAll(messageItems)
        //TODO: Bad practice, should be with DiffUtil!
        notifyDataSetChanged()
    }

}