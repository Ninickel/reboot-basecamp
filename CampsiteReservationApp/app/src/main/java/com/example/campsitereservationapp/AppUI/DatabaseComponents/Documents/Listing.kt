package com.example.campsitereservationapp.AppUI.DatabaseComponents.Documents

import android.location.Location
import com.google.firebase.firestore.DocumentId

class Listing(
    var name: String,
    var address: String,
    var maxOccupancy: Int,
    var description: String,
    var rating: Float,
    var ownerName: String,
    var tags: ArrayList<String>,
    var photoUrls: ArrayList<String>,
    var geoLocation: Location
){
    @DocumentId
    var id: String = ""
}