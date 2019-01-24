package com.tikal.doorbell.android.repository.firebase_repository

import com.tikal.doorbell.android.data.FbRealtimeDatabase
import com.tikal.doorbell.android.data.FirebaseRemoteDatasource
import io.reactivex.Observable

class FirebaseRepository(val remoteDatasource: FirebaseRemoteDatasource) : FbRealtimeDatabase {

    override fun getCode(): Observable<String> {
        return remoteDatasource.getCode()
    }

    override fun updateCode(code: String) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}