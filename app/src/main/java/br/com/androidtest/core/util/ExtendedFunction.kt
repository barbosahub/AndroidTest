package br.com.androidtest.core.util

import android.R.attr.action
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri
import br.com.androidtest.features.myData.domain.model.IconEnum

fun <T> selectUserOrProfile(
    userValue: T?,
    profileValue: T?
): T? = userValue ?: profileValue


@Composable
fun String?.toUiIcon(): Painter {
    val iconEnum = IconEnum.from(this)
    return painterResource(id = iconEnum.drawableRes)
}