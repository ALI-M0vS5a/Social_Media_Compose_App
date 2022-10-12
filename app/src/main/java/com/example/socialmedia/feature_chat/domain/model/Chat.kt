package com.example.socialmedia.feature_chat.domain.model


data class Chat(
  val remoteUsername: String,
  val remoteUserProfileUser: String,
  val lastMessage: String,
  val lastMessageFormattedTime: String
)
