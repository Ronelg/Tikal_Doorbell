package com.tikal.doorbell.android.data.datasources.firebase

import android.widget.Toast
import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class FirebaseRemoteDatesource : FbRealtimeDatabase {

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val doorbellCodeReference: DatabaseReference
    val subject: PublishSubject<String>

    companion object {
        const val DOORBELL_CODE_REFERENCE = "doorbell_code"
    }

    init {
        doorbellCodeReference = database.getReference(DOORBELL_CODE_REFERENCE)
        subject = PublishSubject.create()
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
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Timber.i("###${snapshot.value.toString()}")
                subject.onNext(snapshot.value.toString())
            }

        })
    }
}