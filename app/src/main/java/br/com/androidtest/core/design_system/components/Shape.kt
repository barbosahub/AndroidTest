package br.com.androidtest.core.design_system.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Immutable
data class BaseShapes(
    val none: RoundedCornerShape,
    val pixel: RoundedCornerShape,
    val small: RoundedCornerShape,
    val medium: RoundedCornerShape,
    val large: RoundedCornerShape,
    val full: RoundedCornerShape
)

val LocalAppShapes = staticCompositionLocalOf {
    BaseShapes(
        none = RoundedCornerShape(Dp.Unspecified),
        pixel = RoundedCornerShape(Dp.Unspecified),
        small = RoundedCornerShape(Dp.Unspecified),
        medium = RoundedCornerShape(Dp.Unspecified),
        large = RoundedCornerShape(Dp.Unspecified),
        full = RoundedCornerShape(Dp.Unspecified)
    )
}

val Shapes = BaseShapes(
    none = RoundedCornerShape(0.dp),
    pixel = RoundedCornerShape(1.dp),
    small = RoundedCornerShape(2.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(size = 16.dp),
    full = RoundedCornerShape(100.dp)
)