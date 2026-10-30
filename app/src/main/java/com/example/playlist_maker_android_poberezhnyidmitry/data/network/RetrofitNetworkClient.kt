package com.example.playlist_maker_android_poberezhnyidmitry.data.network

import com.example.playlist_maker_android_poberezhnyidmitry.data.NetworkClient
import com.example.playlist_maker_android_poberezhnyidmitry.data.dto.BaseResponse
import com.example.playlist_maker_android_poberezhnyidmitry.data.dto.TracksSearchResponse

class RetrofitNetworkClient : NetworkClient {
    override fun doRequest(dto: Any): BaseResponse {
        return TracksSearchResponse(emptyList())
    }
}
