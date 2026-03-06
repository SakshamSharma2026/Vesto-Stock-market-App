package com.feature.stock.data.dto

data class StockFinancialData(
    val EndDate: String,
    val FiscalYear: String,
    val StatementDate: String,
    val Type: String,
    val fiscalPeriodNumber: Int,
    val stockFinancialMap: StockFinancialMap
)