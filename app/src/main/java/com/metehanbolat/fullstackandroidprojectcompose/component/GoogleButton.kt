package com.metehanbolat.fullstackandroidprojectcompose.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.metehanbolat.fullstackandroidprojectcompose.R
import com.metehanbolat.fullstackandroidprojectcompose.ui.theme.LoadingBlue
import com.metehanbolat.fullstackandroidprojectcompose.ui.theme.Shapes

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    loadingState: Boolean = false,
    primaryText: String = stringResource(id = R.string.sign_in_with_google),
    secondaryText: String = stringResource(id = R.string.please_wait),
    icon: Int = R.drawable.ic_google_logo,
    shape: Shape = Shapes.medium,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colors.surface,
    borderStokeWidth: Dp = 1.dp,
    progressIndicatorColor: Color = LoadingBlue,
    onClick: () -> Unit
) {
    var buttonText by remember { mutableStateOf(primaryText) }

    LaunchedEffect(key1 = loadingState) {
        buttonText = if (loadingState) secondaryText else primaryText
    }

    Surface(
        modifier = modifier
            .clickable(enabled = !loadingState) {
                onClick()
            },
        shape = shape,
        border = BorderStroke(width = borderStokeWidth, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = stringResource(id = R.string.sign_in_title),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = buttonText)
            if (loadingState) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
            }
        }

    }
}

@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleButton {}
}