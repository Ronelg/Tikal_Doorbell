package com.tikal.doorbell.android.data.datasources.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseRemoteDatesource : FbRealtimeDatabase{

    val database : FirebaseDatabase;
    val doorbellCode : DatabaseReference;

    companion object {
        const val DOORBELL_CODE_REFERENCE = "doorbell_code"
    }

    init {
        database = FirebaseDatabase.getInstance();
        doorbellCode = database.getReference(DOORBELL_CODE_REFERENCE)
    }

    override fun getCode(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateCode(code: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}