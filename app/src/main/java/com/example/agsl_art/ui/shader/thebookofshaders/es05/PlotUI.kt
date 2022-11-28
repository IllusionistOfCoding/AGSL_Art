package com.example.agsl_art.ui.shader.thebookofshaders.es05

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
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
import com.example.agsl_art.ui.shader.PERLIN_NOISE

@OptIn(ExperimentalTextApi::class)
@Composable
@Preview
fun PlotUI() {
    val time by produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it / 1000f
            }
        }
    }

    val shader = RuntimeShader(PLOT_SHADER)
    Column(
        Modifier
            .padding(10.dp)
            .fillMaxSize()
            .onSizeChanged { size ->
                shader.setFloatUniform(
                    "iResolution",
                    size.width.toFloat(),
                    size.height.toFloat()
                )
            }
            .graphicsLayer {
                shader.setFloatUniform("iTime", time)
                renderEffect = RenderEffect
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
            colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF2be4dc)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Click me!")
        }
    }
}