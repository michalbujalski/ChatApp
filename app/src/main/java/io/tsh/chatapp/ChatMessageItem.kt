package io.tsh.chatapp

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.item_chat_message.view.*
import java.text.SimpleDateFormat
import java.util.*

class ChatMessageItem(private val chatMessage: ChatMessage):
        AbstractItem<ChatMessageItem, ChatMessageItem.ChatMessageItemViewHolder>() {

    private val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    override fun getViewHolder(v: View): ChatMessageItemViewHolder {
        return ChatMessageItemViewHolder(v)
    }

    override fun bindView(holder: ChatMessageItemViewHolder, payloads: MutableList<Any>?) {
        super.bindView(holder, payloads)
        holder.nicknameTV.text = chatMessage.nickname
        holder.messageTV.text = chatMessage.message
        holder.dateTV.text = dateFormat.format(Date(chatMessage.date))
    }

    override fun getType(): Int = 0

    override fun getLayoutRes(): Int = R.layout.item_chat_message

    class ChatMessageItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val nicknameTV:TextView = itemView.nicknameTV
        val messageTV:TextView = itemView.messageTV
        val dateTV:TextView = itemView.dateTV
    }
}
