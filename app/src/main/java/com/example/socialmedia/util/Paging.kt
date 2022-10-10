package com.example.socialmedia.util


interface Paging<T> {

    suspend fun loadNextItems()
}