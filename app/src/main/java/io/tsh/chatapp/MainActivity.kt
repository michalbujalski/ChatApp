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
    private var login:MenuItem? = null
    private var logout:MenuItem? = null
    private val fastItemAdapter= FastItemAdapter<ChatMessageItem>()
    private val authRepository:AuthRepository by lazy{
        AuthRepositoryImpl(this)
    }
    private val chatMessagesRepository:ChatMessagesRepository by lazy{
        ChatMessagesRepositoryImpl(this)
    }

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

        sendMessageBtn.setOnClickListener{
            var message = messageET.text.toString()

            if(message.isNotEmpty()){
                chatMessagesRepository.send(message)
                messageET.setText("")
            }
        }
        authRepository.attach()
        chatMessagesRepository.attach()
    }


    override fun onDestroy() {
        authRepository.detach()
        chatMessagesRepository.detach()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat,menu)
        if( menu!=null) {
            login = menu.findItem(R.id.login)
            logout = menu.findItem(R.id.logout)
            setAuthenticated(authRepository.isAuthenticated())
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.login->{
                initSignIn()
            }
            R.id.logout->{
                authRepository.logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun addData(chatMessage: ChatMessage) {
        fastItemAdapter.add(
                ChatMessageItem(chatMessage)
        )
    }

    private fun initSignIn() {
        startActivity(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                                Arrays.asList(
                                        AuthUI.IdpConfig
                                                .Builder(AuthUI.EMAIL_PROVIDER)
                                                .build(),
                                        AuthUI.IdpConfig
                                                .Builder(AuthUI.GOOGLE_PROVIDER)
                                                .build()
                                ))
                        .build()
        )
    }

    override fun setAuthenticated(isAuth: Boolean) {
        login?.isVisible = !isAuth
        logout?.isVisible = isAuth
        messageET.isEnabled = isAuth
        sendMessageBtn.isEnabled = isAuth
    }

}