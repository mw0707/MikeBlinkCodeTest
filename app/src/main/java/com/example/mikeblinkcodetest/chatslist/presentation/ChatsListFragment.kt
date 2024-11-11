package com.example.mikeblinkcodetest.chatslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mikeblinkcodetest.R
import com.example.mikeblinkcodetest.chat.presentation.ChatFragment
import com.example.mikeblinkcodetest.chatslist.domain.model.ChatItem
import com.example.mikeblinkcodetest.chatslist.presentation.recycler.ChatListAdapter
import com.example.mikeblinkcodetest.chatslist.presentation.recycler.OnChatClickListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatsListFragment : Fragment() {

    private val viewModel: ChatsListViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private val adapter = ChatListAdapter(object : OnChatClickListener {
        override fun onChatClick(item: ChatItem) {
            onChatItemClicked(item)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chats_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        observeViewModel()
        viewModel.fetchState()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                when (it) {
                    /*TODO: Add loading in future */
                    is ScreenState.Loading -> {

                    }

                    is ScreenState.Chats -> {
                        updateList(it.items)
                    }

                    is ScreenState.DetailedChat -> {
                        navigateToDetailedChatScreen(it.chatId)
                    }

                    else -> throw IllegalStateException("Invalid screen state")
                }
            }
        }
    }

    private fun updateList(chatItems: List<ChatItem>) {
        adapter.setItems(chatItems)
    }

    private fun onChatItemClicked(chatItem: ChatItem) {
        viewModel.onChatClicked(chatItem)
    }

    private fun navigateToDetailedChatScreen(chatId: String) {
        findNavController().navigate(
            R.id.action_chatsListFragment_to_chatFragment,
            ChatFragment.createArgs(chatId)
        )
    }
}