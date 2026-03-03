package br.com.androidtest.features.myData.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class MyDataDto(
    val success: Boolean? = null,
    val content: ContentDto? = null,
    val screen: ScreenDto? = null
)

@Serializable
data class ContentDto(
    val user: UserDto? = null
)

@Serializable
data class ScreenDto(
    val title: String? = null,
    val profile: UserDto? = null,
    val options: List<OptionDto>? = null
)

@Serializable
data class UserDto(
    val id: String? = null,
    val name: String? = null,
    val age: String? = null,
    val documentNumber: String? = null,
    val avatarUrl: String? = null
)

@Serializable
data class OptionDto(
    val iconUrl: String? = null,
    val iconType: String? = null,
    val title: String? = null,
    val action: String? = null,
    val assetName: String? = null,
    val url: String? = null,
    val modal: ModalDto? = null
)

@Serializable
data class ModalDto(
    val title: String? = null,
    val buttons: List<ButtonDto>? = null
)

@Serializable
data class ButtonDto(
    val title: String? = null,
    val action: String? = null
)