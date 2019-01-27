package com.tikal.doorbell.data.datasources.firebase

import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import timber.log.Timber

class FirebaseRemoteDatasource : FbRealtimeDatabase {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val doorbellCodeReference: DatabaseReference = database.getReference(DOORBELL_CODE_REFERENCE)
    private val subject: Subject<String> = PublishSubject.create<String>()

    init {
        setFirebaseListener()
    }

    override fun getCode(): Observable<String> {
        return subject
    }

    override fun updateCode(code: String) {
        doorbellCodeReference.setValue(code)
    }

    private fun setFirebaseListener() {
        doorbellCodeReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Timber.i("### ${snapshot.value}")
                subject.onNext(snapshot.value.toString())
            }
        })
    }

    companion object {
        private const val DOORBELL_CODE_REFERENCE = "doorbell_code"
    }
}