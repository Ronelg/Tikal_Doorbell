package com.tikal.doorbell.android.data

import io.reactivex.Observable

interface FbRealtimeDatabase {
    fun getCode(): Observable<String>
    fun updateCode(code: String)
}