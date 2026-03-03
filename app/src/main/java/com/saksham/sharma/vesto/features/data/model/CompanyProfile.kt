package com.saksham.sharma.vesto.features.data.model

data class CompanyProfile(
    val companyDescription: String,
    val exchangeCodeBse: String,
    val exchangeCodeNse: String,
    val isInId: String,
    val mgIndustry: String,
    val officers: Officers,
    val peerCompanyList: List<PeerCompany>
)