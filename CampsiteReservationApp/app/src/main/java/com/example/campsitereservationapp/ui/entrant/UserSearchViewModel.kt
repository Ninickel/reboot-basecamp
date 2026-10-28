package com.example.campsitereservationapp.ui.entrant

import com.example.campsitereservationapp.AppUI.DatabaseComponents.Documents.Listing
import com.example.campsitereservationapp.AppUI.DatabaseComponents.Models.DatabaseModel
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class UserSearchViewModel: DatabaseModel() {
    var searchResults = ArrayList<Listing>()

    suspend fun doSearch(keyword: String, selectedDates: Set<LocalDate>, location: String, capacity: Int){
        searchResults.clear() 
        var queryResult = listingsRef.whereGreaterThanOrEqualTo("name", keyword)
            .whereLessThanOrEqualTo("name", keyword + "\\uf8ff").get().await() //search by keyword

        for (document in queryResult){
            var listing = document.toObject(Listing::class.java)
            if(meetsDateRequirements(selectedDates, listing) &&
                listing.address == location &&
                listing.maxOccupancy >= capacity){
                searchResults.add(listing)
            }
        }
    }

    fun meetsDateRequirements(selectedDates: Set<LocalDate>, listing: Listing): Boolean{
        var availableDates = mutableSetOf<LocalDate>()
        for(lottery in _lotteries){
            if(listing.id in lottery.campsiteIds){
                for(date in lottery.dates){
                    availableDates.add(date)
                }
            }
        }
        return (selectedDates - availableDates).isEmpty() //if all selected dates are in available dates then requirement satisfied
    }
}