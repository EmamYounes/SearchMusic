package com.example.searchmusic.pojo

import com.google.gson.annotations.SerializedName

data class ClientTokenReponse(

	@field:SerializedName("expiresIn")
	val expiresIn: String? = null,

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("tokenType")
	val tokenType: String? = null
)
