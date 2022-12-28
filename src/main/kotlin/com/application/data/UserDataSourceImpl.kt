package com.application.data

import com.application.domain.UserDataSource
import com.application.domain.model.ApiCreateUserRequest
import com.application.domain.model.ApiUserResponse
import com.application.domain.model.UserEntity
import com.application.utils.ApplicationException
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValue

class UserDataSourceImpl(
   database: CoroutineDatabase
) : UserDataSource {

   private val users = database.getCollection<UserEntity>()

   override suspend fun getUserInfo(userId: String): ApiUserResponse {
      val user = users.findOne(filter = UserEntity::id eq userId)
      return if (user != null) {
         ApiUserResponse(
            message = "User: ${user.id}",
            user = user
         )
      } else {
         throw ApplicationException(
            cause = Throwable(message = "User not found.")
         )
      }
   }

   override suspend fun saveUserInfo(user: ApiCreateUserRequest): ApiUserResponse {
      val newUser = UserEntity(
         id = ObjectId().toString(),
         name = user.name,
         emailAddress = user.emailAddress,
         profilePhoto = user.profilePhoto
      )
      val isSaved = users.insertOne(document = newUser).wasAcknowledged()
      return if (isSaved) {
         ApiUserResponse(
            user = newUser,
            message = "User Successfully created!"
         )
      } else {
         throw ApplicationException(
            cause = Throwable(message = "User create task failed.")
         )
      }
   }

   override suspend fun deleteUser(userId: String): ApiUserResponse {
      val isDeleted = users.deleteOne(filter = UserEntity::id eq userId).wasAcknowledged()
      return if (isDeleted) {
         ApiUserResponse(
            message = "User Successfully deleted."
         )
      } else {
         throw ApplicationException(
            cause = Throwable(message = "User delete task failed! User not found.")
         )
      }
   }

   override suspend fun updateUserInfo(userId: String, firstName: String, lastName: String): ApiUserResponse {
      val updatedUser = users.findOneAndUpdate(
         UserEntity::id eq userId, update = setValue(
            property = UserEntity::name,
            value = "$firstName $lastName"
         )
      )
      return if (updatedUser != null) {
         ApiUserResponse(
            message = "User Successfully up to date!",
            user = updatedUser
         )
      } else {
         throw ApplicationException(
            cause = Throwable(message = "Update task failed! User not found.")
         )
      }
   }

}