package io.tsh.chatapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity: AppCompatActivity(), ChatView, AuthView {

    private val fastItemAdapter= FastItemAdapter<ChatMessageItem>()

    private val linearLayoutManager:LinearLayoutManager by lazy{
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = fastItemAdapter

        fastItemAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                linearLayoutManager
                        .smoothScrollToPosition(recyclerView, null, fastItemAdapter.itemCount)
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun addData(chatMessage: ChatMessage) {
    }

    private fun initSignIn() {

    }

    override fun setAuthenticated(isAuth: Boolean) {
    }

}