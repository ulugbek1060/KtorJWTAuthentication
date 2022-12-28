package com.application.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiUserResponse(
   val user: UserEntity? = null,
   val message: String? = null
)
