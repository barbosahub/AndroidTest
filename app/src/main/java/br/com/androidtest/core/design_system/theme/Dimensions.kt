package br.com.androidtest.core.design_system.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


object Dimensions {
    val spacing: Spacing = Spacing()
    val componentHeightSize: ComponentHeightSize = ComponentHeightSize()
    val componentWidthSize: ComponentWidthSize = ComponentWidthSize()
    val avatarImageSize: AvatarImageSize = AvatarImageSize()

    val itemHeight: Item = Item()
    val borderWidth: BorderWidth = BorderWidth()
    val fontSize: FontSize = FontSize()
}

data class Spacing(
    val spaceNone: Dp = 0.dp,
    val space4dp: Dp = 4.dp,
    val space8dp: Dp = 8.dp,
    val space12dp: Dp = 12.dp,
    val space16dp: Dp = 16.dp,
    val space24dp: Dp = 24.dp
)

data class Item(
    val m: Dp = 48.dp
)

data class ComponentWidthSize(
    val modalDefault: Dp = 320.dp,
)

data class ComponentHeightSize(
    val buttonS: Dp = 32.dp,
    val buttonM: Dp = 40.dp,
    val buttonL: Dp = 48.dp,

    val modalHeader: Dp = 64.dp

)

data class BorderWidth(
    val s: Dp = 0.5.dp,
    val m: Dp = 1.dp,
    val l: Dp = 2.dp
)

data class AvatarImageSize(
    val default: Dp = 100.dp
)

data class FontSize( // Criado apenas como demonstração, porém o ideal é criar um Type/Typography com as fontes e tamanho de acordo com o material design.
    val heading: TextUnit = 24.sp,
    val title: TextUnit = 14.sp,
    val body: TextUnit = 12.sp,
    val label: TextUnit = 10.sp,
)