package com.example.trafikkskilt

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun saveToDb(speedLimit: Int, latitude: Double, longitude: Double) {
    val sign = hashMapOf(
        "speedlimit" to speedLimit,
         "latitude" to latitude,
        "longitude" to longitude,
    )

    val db = Firebase.firestore

    db.collection("speedsigns")
        .add(sign)
        .addOnSuccessListener { documentReference ->
            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }

}