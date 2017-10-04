package io.tsh.chatapp

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class AuthRepositoryImpl(private val authView:AuthView):AuthRepository {
    private val auth = FirebaseAuth.getInstance()


    override fun isAuthenticated(): Boolean {
        return false
    }

    override fun attach() {
    }

    override fun detach() {
    }

    override fun logout() {
        auth.signOut();
    }
}