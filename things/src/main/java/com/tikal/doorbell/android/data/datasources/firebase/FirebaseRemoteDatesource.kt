package com.tikal.doorbell.android.data.datasources.firebase

import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FirebaseRemoteDatesource : FbRealtimeDatabase{

    val database : FirebaseDatabase
    val doorbellCodeReference : DatabaseReference
    val subject : PublishSubject<String>

    companion object {
        const val DOORBELL_CODE_REFERENCE = "doorbell_code"
    }

    init {
        database = FirebaseDatabase.getInstance();
        doorbellCodeReference = database.getReference(DOORBELL_CODE_REFERENCE)
        subject = PublishSubject.create()
        setFirebaseListener()
    }

    override fun getCode(): Observable<String> {
        return subject
    }

    override fun updateCode(code: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun setFirebaseListener(){
        val addValueEventListener = doorbellCodeReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                subject.onNext(snapshot.value as String)
            }

        })
    }
}