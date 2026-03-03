package br.com.androidtest.features.myPlan.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MyPlanDto(
    val success: Boolean? = null,
    val content: ContentDto? = null,
    val screen: ScreenDto? = null
)

@Serializable
data class ContentDto(
    val planValue: String? = null,
    val status: String? = null,
    val phoneNumber: String? = null,
    val plan: Int? = null,
    val bonus: Int? = null,
    val extraPlayBaseUrl: String? = null,
    val extraPlay: String? = null
)

@Serializable
data class ScreenDto(
    val name: String? = null,
    val offerDisplay: String? = null,
    val planValue: String? = null,
    val header: List<List<HeaderItemDto>>? = null,
    val extraPlay: ExtraPlayDto? = null
)

@Serializable
data class HeaderItemDto(
    val title: String? = null,
    val value: HeaderValueDto? = null
)

@Serializable
data class HeaderValueDto(
    val text: String? = null,
    val alignment: String? = null,
    val textColor: String? = null
)

@Serializable
data class ExtraPlayDto(
    val title: String? = null,
    val options: List<ExtraPlayOptionDto>? = null
)

@Serializable
data class ExtraPlayOptionDto(
    val description: String? = null,
    val url: String? = null
)