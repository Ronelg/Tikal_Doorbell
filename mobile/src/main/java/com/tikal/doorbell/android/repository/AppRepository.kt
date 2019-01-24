package com.tikal.doorbell.android.repository

import data.datasources.firebase.FirebaseRemoteDatasource
import data.datasources.firebase.repositories.firebase_repository.FirebaseRepository
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