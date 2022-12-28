package com.application.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
   val id: String,
   val name: String,
   val emailAddress: String,
   val profilePhoto: String
)