package com.feature.stock.data.dto

data class Financial(
    val EndDate: String,
    val FiscalYear: String,
    val StatementDate: String,
    val Type: String,
    val fiscalPeriodNumber: Int,
    val stockFinancialMap: StockFinancialMap
)