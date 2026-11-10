package com.example.playlist_maker_android_poberezhnyidmitry.data

import com.example.playlist_maker_android_poberezhnyidmitry.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
}
