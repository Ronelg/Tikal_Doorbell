package com.tikal.doorbell.android.repository

import com.tikal.doorbell.data.datasources.firebase.FirebaseRemoteDatasource
import com.tikal.doorbell.data.repository.FirebaseRepository
import io.reactivex.Observable

class AppRepository {

    val firebaseRepository: FirebaseRepository

    init {
        firebaseRepository = FirebaseRepository(FirebaseRemoteDatasource())
    }

    fun getDoorCode(): Observable<String> {
        return firebaseRepository.getCode()
    }

}