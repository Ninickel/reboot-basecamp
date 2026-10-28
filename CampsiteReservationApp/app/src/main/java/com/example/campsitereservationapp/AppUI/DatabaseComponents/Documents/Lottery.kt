package com.example.campsitereservationapp.AppUI.DatabaseComponents.Documents

import com.google.firebase.firestore.DocumentId
import java.time.LocalDate
import java.util.Date

class Lottery(var dates: ArrayList<LocalDate>, val start: LocalDate, val end: LocalDate, var campsiteIds: ArrayList<String>) {

    /** Firestore document id, populated automatically on read via [DocumentId]. */
    @DocumentId
    var id: String = ""

    var waitlist = ArrayList<Profile>()

    /** Adds [entrant] to this lottery's waitlist. */
    fun addEntrant(entrant: Profile) {
        waitlist.add(entrant)
    }
}