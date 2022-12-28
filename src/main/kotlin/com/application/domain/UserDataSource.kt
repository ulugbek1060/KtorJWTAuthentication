package com.application.domain

import com.application.domain.model.ApiCreateUserRequest
import com.application.domain.model.ApiUserResponse

interface UserDataSource {
   suspend fun getUserInfo(userId: String): ApiUserResponse
   suspend fun saveUserInfo(user: ApiCreateUserRequest): ApiUserResponse
   suspend fun deleteUser(userId: String): ApiUserResponse
   suspend fun updateUserInfo(userId: String, firstName: String, lastName: String): ApiUserResponse
}