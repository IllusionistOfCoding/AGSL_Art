package com.example.agsl_art.ui.shader

import android.graphics.RuntimeShader
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue


@OptIn(ExperimentalTextApi::class)
@Composable
@Preview
fun PerlinNoiseUI() {
    val time by produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it / 1000f
            }
        }
    }

    val shader = RuntimeShader(PERLIN_NOISE)
    Column(
        Modifier
        .padding(10.dp)
        .fillMaxSize()
        .onSizeChanged { size ->
            shader.setFloatUniform(
                "resolution",
                size.width.toFloat(),
                size.height.toFloat()
            )
        }
        .graphicsLayer {
            shader.setFloatUniform("time", time)
            renderEffect = android.graphics.RenderEffect
                .createRuntimeShaderEffect(
                    shader,
                    "contents"
                )
                .asComposeRenderEffect()
        }
    ) {
        Text(
            "Hello Noisy Text",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                brush = Brush.linearGradient(listOf(Color(0xFF2be4dc), Color(0xFF243484))),
                fontWeight = FontWeight.ExtraBold, fontSize = 90.sp
            )
        )
        Button(
            onClick = {},
            colors = buttonColors(contentColor = Color(0xFF2be4dc)),
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text("Click me!")
        }
    }
}