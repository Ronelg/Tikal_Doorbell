package com.tikal.doorbell.data.datasources.firebase

import io.reactivex.Observable

interface FbRealtimeDatabase {
    fun getCode(): Observable<String>
    fun updateCode(code: String)
}