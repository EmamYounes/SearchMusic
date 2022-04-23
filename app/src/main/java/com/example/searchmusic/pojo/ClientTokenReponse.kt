package com.example.searchmusic.pojo


data class ClientTokenResponse(

    var expiresIn: String? = "",
    var accessToken: String? = "",
    var tokenType: String? = ""
)
