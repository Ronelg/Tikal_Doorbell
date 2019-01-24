package com.tikal.doorbell.android.repository

import com.tikal.doorbell.android.data.FirebaseRemoteDatasource
import com.tikal.doorbell.android.repository.firebase_repository.FirebaseRepository
import io.reactivex.Observable

class AppRepository {

    val firebaseRepository : FirebaseRepository


    init {
        firebaseRepository = FirebaseRepository(FirebaseRemoteDatasource())
    }

    fun getDoorCode() : Observable<String>{
        return  firebaseRepository.getCode()
    }

}