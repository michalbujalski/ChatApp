package io.tsh.chatapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.HashMap

class ChatMessagesRepositoryImpl(private val chatView: ChatView) : ChatMessagesRepository {
    private val ref: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("chat")
    }


    override fun attach() {
    }

    override fun send(message: String) {
        ref.push().setValue(
                ChatMessage(
                        FirebaseAuth.getInstance().currentUser?.displayName ?: "",
                        message,
                        System.currentTimeMillis()
                )
        )
    }

    override fun detach() {
    }
}