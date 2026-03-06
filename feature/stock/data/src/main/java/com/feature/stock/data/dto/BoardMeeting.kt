package com.feature.stock.data.dto

data class BoardMeeting(
    val boardMeetDate: String,
    val companyName: String,
    val purpose: String,
    val remarks: String,
    val tickerId: String
)