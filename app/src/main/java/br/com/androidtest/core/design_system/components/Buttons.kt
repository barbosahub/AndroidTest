package br.com.androidtest.core.design_system.components

import android.R.style.Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import br.com.androidtest.R
import br.com.androidtest.core.design_system.theme.AppTheme
import br.com.androidtest.core.design_system.theme.Dimensions


@Preview
@Composable
fun ButtonsPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .background(color = colorResource(R.color.bgPrimary))
        ) {
            Column(
                modifier = Modifier.padding(Dimensions.spacing.space16dp),
                verticalArrangement = Arrangement.spacedBy(Dimensions.spacing.space16dp)
            ) {
                ButtonPrimary(
                    modifier = Modifier.fillMaxWidth(),
                    labelText = "Label",
                    onClick = {},
                    size = ButtonSpecsSize.M
                )

                ButtonSecondary(
                    modifier = Modifier.fillMaxWidth(),
                    labelText = "Label",
                    onClick = {},
                    size = ButtonSpecsSize.M
                )
            }
        }
    }
}


@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    size: ButtonSpecsSize = ButtonSpecsSize.M,
    onClick: () -> Unit,
    labelText: String = ""
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val containerColorByState = when {
        isPressed -> colorResource(R.color.bgBrandSolidPressed)
        isHovered -> colorResource(R.color.bgBrandSolid)
        else -> colorResource(R.color.bgBrandSolid)
    }


    val focusBorderModifier = if (isFocused) {
        modifier
            .border(
                shape = Shapes.full,
                width = Dimensions.borderWidth.l,
                color = Color.Black
            )
    } else {
        modifier
    }

    Box(modifier = focusBorderModifier.height(size.height)) {
        Button(
            modifier = modifier.fillMaxHeight(),
            contentPadding = PaddingValues(horizontal = size.horizontalPadding),
            onClick = {
                onClick()
            },
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColorByState,
                contentColor = colorResource(R.color.white)
            ),
            shape = Shapes.full,
            content = {
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = labelText.replaceFirstChar { it.uppercase() },
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        )
    }
}

@Composable
fun ButtonSecondary(
    modifier: Modifier = Modifier,
    size: ButtonSpecsSize = ButtonSpecsSize.M,
    onClick: () -> Unit,
    labelText: String = ""
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val containerColorByState = when {
        isPressed -> colorResource(R.color.bgPrimaryPressed)
        isHovered -> colorResource(R.color.bgPrimaryPressed)
        else -> colorResource(R.color.bgPrimary)
    }

    val borderColorByState = when {
        isPressed -> colorResource(R.color.border)
        isHovered -> colorResource(R.color.border)
        else -> colorResource(R.color.border)
    }

    val focusBorderModifier = if (isFocused) {
        modifier
            .border(
                shape = Shapes.full,
                width = Dimensions.borderWidth.l,
                color = colorResource(R.color.border)
            )
    } else {
        modifier
    }

    Box(modifier = focusBorderModifier.height(size.height)) {
            Button(
                modifier = modifier.fillMaxHeight(),
                contentPadding = PaddingValues(horizontal = size.horizontalPadding),
                border = BorderStroke(
                    width = Dimensions.borderWidth.m,
                    color = borderColorByState
                ),
                onClick = {
                        onClick()

                },
                interactionSource = interactionSource,
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColorByState,
                    contentColor = colorResource(R.color.textPrimary)
                ),
                shape = Shapes.full,
                content = {
                    Row(
                        modifier = modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = labelText.replaceFirstChar { it.uppercase() },
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            )

    }
}


enum class ButtonSpecsSize(val height: Dp, val horizontalPadding: Dp) {
    M(
        height = Dimensions.componentHeightSize.buttonM,
        horizontalPadding = Dimensions.spacing.space12dp
    )
}
