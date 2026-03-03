package br.com.androidtest.features.myPlan.domain.model

data class MyPlan(
    val content: Content? = null,
    val screen: Screen? = null
)

data class Content(
    val planValue: String? = null,
    val status: String? = null,
    val phoneNumber: String? = null,
    val plan: Int? = null,
    val bonus: Int? = null,
    val extraPlayBaseUrl: String? = null,
    val extraPlay: List<ExtraPlayOption>? = null
)

data class Screen(
    val name: String? = null,
    val offerDisplay: String? = null,
    val planValue: String? = null,
    val header: List<List<HeaderItem>>? = null,
    val extraPlay: ExtraPlay? = null
)

data class HeaderItem(
    val title: String? = null,
    val value: HeaderValue? = null
)

data class HeaderValue(
    val text: String? = null,
    val alignment: String? = null,
    val textColor: String? = null
)

data class ExtraPlay(
    val title: String? = null,
    val options: List<ExtraPlayOption>? = null
)

data class ExtraPlayOption(
    val description: String? = null,
    val url: String? = null
)