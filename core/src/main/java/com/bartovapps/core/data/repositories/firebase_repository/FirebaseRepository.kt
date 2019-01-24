package com.bartovapps.core.data.repositories.firebase_repository

import com.bartovapps.core.data.datasources.firebase.FbRealtimeDatabase
import com.bartovapps.core.data.datasources.firebase.FirebaseRemoteDatasource
import io.reactivex.Observable

class FirebaseRepository(val remoteDatasource: FirebaseRemoteDatasource) : FbRealtimeDatabase {

    override fun getCode(): Observable<String> {
        return remoteDatasource.getCode()
    }

    override fun updateCode(code: String) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}