package io.tsh.chatapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.HashMap

class ChatMessagesRepositoryImpl(private val chatView: ChatView) : ChatMessagesRepository {
    private val ref: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("chat")
    }
    private val listener = object: ChildEventListener {
        override fun onCancelled(p0: DatabaseError?) {}

        override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}

        override fun onChildChanged(p0: DataSnapshot?, p1: String?) {}

        override fun onChildAdded(p0: DataSnapshot, p1: String?) {
            val chatMsg = ChatMessage.create(p0.value as HashMap<*, *>)
            chatView.addData(chatMsg)
        }
        override fun onChildRemoved(p0: DataSnapshot?) {}
    }

    override fun attach() {
        ref.limitToLast(10).addChildEventListener(listener)
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
        ref.removeEventListener(listener)
    }
}