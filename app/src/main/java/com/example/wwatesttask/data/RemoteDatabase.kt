package com.example.wwatesttask.data


import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDatabase @Inject constructor() : Database {
    private val database = FirebaseDatabase.getInstance()

    override suspend fun getUrl(): String {
        val deferred = CompletableDeferred<String>()
        withContext(Dispatchers.IO) {
            val ref = database.getReference("url")

            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val url = snapshot.value
                    deferred.complete(url as String)
                }

                override fun onCancelled(error: DatabaseError) {
                    deferred.completeExceptionally(error.toException())
                }
            })


        }
        return deferred.await()

    }
}
