package com.saksham.sharma.vesto.features.data.model

data class StockCorporateActionData(
    val annualGeneralMeeting: List<AnnualGeneralMeeting>,
    val boardMeetings: List<BoardMeeting>,
    val bonus: List<Any>,
    val dividend: List<Any>,
    val rights: List<Any>,
    val splits: List<Any>
)