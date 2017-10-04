package io.tsh.chatapp

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class AuthRepositoryImpl(private val authView:AuthView):AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val listener = FirebaseAuth.AuthStateListener{
        authView.setAuthenticated(isAuthenticated())
    }

    override fun isAuthenticated(): Boolean {
        return auth.currentUser != null
    }

    override fun attach() {
        auth.addAuthStateListener(listener)
    }

    override fun detach() {
        auth.removeAuthStateListener(listener)
    }

    override fun logout() {
        auth.signOut();
    }
}