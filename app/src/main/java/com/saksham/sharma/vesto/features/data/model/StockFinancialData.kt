package com.saksham.sharma.vesto.features.data.model

data class StockFinancialData(
    val EndDate: String,
    val FiscalYear: String,
    val StatementDate: String,
    val Type: String,
    val fiscalPeriodNumber: Int,
    val stockFinancialMap: StockFinancialMap
)