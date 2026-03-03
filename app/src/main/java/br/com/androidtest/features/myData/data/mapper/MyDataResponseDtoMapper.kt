package br.com.androidtest.features.myData.data.mapper

import br.com.androidtest.features.myData.data.dto.ButtonDto
import br.com.androidtest.features.myData.data.dto.ContentDto
import br.com.androidtest.features.myData.data.dto.ModalDto
import br.com.androidtest.features.myData.data.dto.MyDataDto
import br.com.androidtest.features.myData.data.dto.OptionDto
import br.com.androidtest.features.myData.data.dto.ScreenDto
import br.com.androidtest.features.myData.data.dto.UserDto
import br.com.androidtest.features.myData.domain.model.Button
import br.com.androidtest.features.myData.domain.model.Content
import br.com.androidtest.features.myData.domain.model.Modal
import br.com.androidtest.features.myData.domain.model.MyData
import br.com.androidtest.features.myData.domain.model.Option
import br.com.androidtest.features.myData.domain.model.Screen
import br.com.androidtest.features.myData.domain.model.User


fun MyDataDto.toDomainData(): MyData {
    return MyData(
        content = content?.toDomainContent(), screen = screen?.toDomainScreen()
    )
}

fun ContentDto.toDomainContent(): Content {

    return Content(
        user = this.user?.toDomainUser()
    )
}

private fun UserDto.toDomainUser(): User {
    return User(
        id = this.id,
        name = this.name,
        age = this.age,
        documentNumber = this.documentNumber,
        documentNumberMasked = this.documentNumber.mask(),
        avatarUrl = this.avatarUrl,
    )
}

private fun ScreenDto.toDomainScreen(): Screen {
    return Screen(
        title = this.title, profile = this.profile?.toDomainUser(), options = this.options?.map {
            it.toDomainOption()
        })
}

private fun OptionDto.toDomainOption(): Option {
    return Option(
        iconUrl = this.iconUrl,
        iconType = this.iconType,
        title = this.title,
        assetName = this.assetName,
        url = this.url,
        modal = this.modal?.toDomainModal()
    )
}

private fun ModalDto.toDomainModal(): Modal {
    return Modal(
        title = this.title, buttons = this.buttons?.map {
            it.toDomainButton()
        })
}

private fun ButtonDto.toDomainButton(): Button {
    return Button(
        title = this.title, action = this.action
    )
}

fun String?.mask(): String {
    if (this.isNullOrBlank()) return ""
    val regex = "(\\d{3})\\.(\\d{3})\\.(\\d{3})-(\\d{2})".toRegex()
    return this.replace(regex) { matchResult ->
        val part1 = matchResult.groupValues[1]
        val part2 = "XXX.XXX"
        val part4 = matchResult.groupValues[4]
        "$part1.$part2-$part4"
    }
}
