package com.feature.stock.data.dto

data class StockCorporateActionData(
    val annualGeneralMeeting: List<AnnualGeneralMeeting>,
    val boardMeetings: List<BoardMeeting>,
    val bonus: List<Any>,
    val dividend: List<Any>,
    val rights: List<Any>,
    val splits: List<Any>
)