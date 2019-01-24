package com.tikal.doorbell.android.repository

import com.bartovapps.core.data.datasources.firebase.FirebaseRemoteDatasource
import com.bartovapps.core.data.repositories.firebase_repository.FirebaseRepository
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