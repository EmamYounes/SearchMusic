package com.example.searchmusic.model

import com.example.searchmusic.pojo.ClientTokenReponse

class MusicDataContract {
    interface Repository {

        fun callGetTokenApi()

    }

    interface Remote {
        fun callGetTokenApi(): ClientTokenReponse
    }
}