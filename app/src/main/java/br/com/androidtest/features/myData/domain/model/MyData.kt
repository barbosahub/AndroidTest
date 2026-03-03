package br.com.androidtest.features.myData.domain.model


data class MyData(
    val content: Content? = null,
    val screen: Screen? = null
)

data class Content(
    val user: User? = null
)

data class Screen(
    val title: String? = null,
    val profile: User? = null,
    val options: List<Option>? = null
)

data class User(
    val id: String? = null,
    val name: String? = null,
    val age: String? = null,
    val documentNumber: String? = null,
    val documentNumberMasked: String? = null,
    val avatarUrl: String? = null
)

data class Option(
    val iconUrl: String? = null,
    val iconType: String? = null,
    val title: String? = null,
    val action: String? = null,
    val assetName: String? = null,
    val url: String? = null,
    val modal: Modal? = null
)

data class Modal(
    val title: String? = null,
    val buttons: List<Button>? = null
)

data class Button(
    val title: String? = null,
    val action: String? = null
)