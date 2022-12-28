package com.application.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiCreateUserRequest(
   val name: String,
   val emailAddress: String,
   val profilePhoto: String
)