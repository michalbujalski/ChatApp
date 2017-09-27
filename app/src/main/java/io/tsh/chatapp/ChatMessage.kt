package io.tsh.chatapp

import java.util.*
import kotlin.collections.HashMap

data class ChatMessage(
        val nickname:String,
        val message:String,
        val date:Long) {
    companion object Factory {
        fun create(map: HashMap<*, *>):ChatMessage {
            return ChatMessage(
                    map["nickname"] as String,
                    map["message"] as String,
                    map["date"] as Long)
        }
    }
}