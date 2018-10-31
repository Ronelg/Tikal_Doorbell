package com.tikal.doorbell.android.data.datasources.firebase

import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import timber.log.Timber

class FirebaseRemoteDatesource : FbRealtimeDatabase {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val doorbellCodeReference: DatabaseReference
    private val subject: Subject<String> = PublishSubject.create<String>()

    companion object {
        private const val DOORBELL_CODE_REFERENCE = "doorbell_code"
    }

    init {
        doorbellCodeReference = database.getReference(DOORBELL_CODE_REFERENCE)
        setFirebaseListener()
    }

    override fun getCode(): Observable<String> {
        return subject
    }

    override fun updateCode(code: String) {
        doorbellCodeReference.setValue(code);
    }

    private fun setFirebaseListener() {
        doorbellCodeReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Timber.i("###${snapshot.value}")
                subject.onNext(snapshot.value.toString())
            }

        })
    }
}