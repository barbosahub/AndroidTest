package br.com.androidtest.features.myPlan.data.mapper

import br.com.androidtest.features.myPlan.data.dto.ContentDto
import br.com.androidtest.features.myPlan.data.dto.ExtraPlayDto
import br.com.androidtest.features.myPlan.data.dto.ExtraPlayOptionDto
import br.com.androidtest.features.myPlan.data.dto.HeaderItemDto
import br.com.androidtest.features.myPlan.data.dto.HeaderValueDto
import br.com.androidtest.features.myPlan.data.dto.MyPlanDto
import br.com.androidtest.features.myPlan.data.dto.ScreenDto
import br.com.androidtest.features.myPlan.domain.model.Content
import br.com.androidtest.features.myPlan.domain.model.ExtraPlay
import br.com.androidtest.features.myPlan.domain.model.ExtraPlayOption
import br.com.androidtest.features.myPlan.domain.model.HeaderItem
import br.com.androidtest.features.myPlan.domain.model.HeaderValue
import br.com.androidtest.features.myPlan.domain.model.MyPlan
import br.com.androidtest.features.myPlan.domain.model.Screen


fun MyPlanDto.toDomainPlan(): MyPlan {
    return MyPlan(
        content = content?.toDomainContent(),
        screen = screen?.toDomainScreen()
    )
}

private fun ContentDto.toDomainContent(): Content {
    return Content(
        planValue = this.planValue,
        status = this.status,
        phoneNumber = this.phoneNumber,
        plan = this.plan,
        bonus = this.bonus,
        extraPlayBaseUrl = this.extraPlayBaseUrl,
        extraPlay = this.extraPlay?.split(";")?.map { fileName ->
            ExtraPlayOption(
                description = fileName.substringBefore("."),
                url = "${this.extraPlayBaseUrl}/$fileName"
            )
        }
    )
}

private fun ScreenDto.toDomainScreen(): Screen {
    return Screen(
        name = this.name,
        offerDisplay = this.offerDisplay,
        planValue = this.planValue,
        header = this.header?.map { row ->
            row.map { it.toDomainHeaderItem() }
        },
        extraPlay = this.extraPlay?.toDomainExtraPlay()
    )
}

private fun HeaderItemDto.toDomainHeaderItem(): HeaderItem {
    return HeaderItem(
        title = this.title,
        value = this.value?.toDomainHeaderValue()
    )
}

private fun HeaderValueDto.toDomainHeaderValue(): HeaderValue {
    return HeaderValue(
        text = this.text,
        alignment = this.alignment,
        textColor = this.textColor
    )
}

private fun ExtraPlayDto.toDomainExtraPlay(): ExtraPlay {
    return ExtraPlay(
        title = this.title,
        options = this.options?.map {
            it.toDomainExtraPlayOption()
        }
    )
}

private fun ExtraPlayOptionDto.toDomainExtraPlayOption(): ExtraPlayOption {
    return ExtraPlayOption(
        description = this.description,
        url = this.url
    )
}
