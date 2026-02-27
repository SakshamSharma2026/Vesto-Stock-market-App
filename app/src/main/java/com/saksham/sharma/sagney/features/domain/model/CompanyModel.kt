package com.saksham.sharma.sagney.features.domain.model

import com.saksham.sharma.sagney.R

data class Company(
    val name: String,
    val ticker: String,
    val iconPath: Int
)

val companyList1 = listOf(
    Company("Spotify", "SPOT", R.drawable.spotify),
    Company("Meta", "FB", R.drawable.meta),
    Company("Apple", "AAPL", R.drawable.apple),
    Company("Amazon", "AMZN", R.drawable.amazon)
)
val companyList2 = listOf(
    Company("Alibaba", "BABA", R.drawable.alibaba),
    Company("Google", "GOOG", R.drawable.google),
    Company("Netflix", "NFLX", R.drawable.netflix),
    Company("Tesla", "TSLA", R.drawable.tesla)
)
val companyList3 = listOf(
    Company("Microsoft", "MSFT", R.drawable.microsoft),
    Company("Paypal", "PYPL", R.drawable.paypal),
    Company("Pinterest", "PINS", R.drawable.pinterest),
    Company("Youtube", "YOUTUBE", R.drawable.youtube)
)