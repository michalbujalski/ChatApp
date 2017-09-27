package io.tsh.chatapp

interface ChatMessagesRepository {
    fun attach()
    fun send(message:String)
    fun detach()
}