package com.example.mikeblinkcodetest.chat.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.mikeblinkcodetest.R
import com.example.mikeblinkcodetest.chat.domain.model.MessageItem
import com.example.mikeblinkcodetest.chat.presentation.recycler.ChatAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val CHAT_ID = "chatId"

class ChatFragment : Fragment() {
    private val chatId by lazy { requireArguments().getString(CHAT_ID) }

    private val viewModel: ChatViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var sendButton: Button
    private lateinit var messageEditText: EditText
    private val adapter = ChatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.messages_recycler_view)
        sendButton = view.findViewById(R.id.send_message_button)
        messageEditText = view.findViewById(R.id.message_edit_text)
        recyclerView.adapter = adapter

        observeViewModel()

        chatId?.let {
            viewModel.fetchState(it)
        } ?: {
            throw IllegalStateException("Invalid argument passed")
        }

        configureSendButton()
    }

    private fun configureSendButton() {
        //TODO: Better to add debounce and EditText validation
        sendButton.setOnClickListener {
            chatId?.let {

                viewModel.dispatch(
                    ChatViewModel.ScreenEvent.SendMessage(
                        it,
                        messageEditText.text.toString()
                    )
                )

                messageEditText.text.clear()
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                when (it) {
                    /*TODO: Add loading in future */
                    is ChatViewModel.ScreenState.Loading -> {

                    }

                    is ChatViewModel.ScreenState.Messages -> {
                        updateList(it.items)
                    }

                    else -> throw IllegalStateException("Invalid screen state")
                }
            }
        }
    }

    private fun updateList(messageItems: List<MessageItem>) {
        adapter.addItems(messageItems)
    }

    companion object {
        fun createArgs(chatId: String): Bundle {
            return bundleOf(CHAT_ID to chatId)
        }
    }
}