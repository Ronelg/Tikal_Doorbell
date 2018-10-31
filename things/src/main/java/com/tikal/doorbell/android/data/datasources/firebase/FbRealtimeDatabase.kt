package com.tikal.doorbell.android.data.datasources.firebase

interface FbRealtimeDatabase {
    fun getCode() : String
    fun updateCode(code : String)
}