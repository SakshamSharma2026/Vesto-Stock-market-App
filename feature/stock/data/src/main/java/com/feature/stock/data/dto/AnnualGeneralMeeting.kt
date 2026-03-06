package com.feature.stock.data.dto

data class AnnualGeneralMeeting(
    val agmDate: String,
    val companyName: String,
    val dateOfAnnouncement: String,
    val purpose: String,
    val recordDate: String,
    val remarks: String,
    val tickerId: String
)