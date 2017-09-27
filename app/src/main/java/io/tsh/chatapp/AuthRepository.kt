package io.tsh.chatapp

interface AuthRepository {
    fun isAuthenticated():Boolean
    fun attach()
    fun detach()
    fun logout()
}