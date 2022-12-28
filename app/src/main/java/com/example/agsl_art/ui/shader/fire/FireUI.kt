package com.example.agsl_art.ui.shader.fire

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.agsl_art.ui.shader.CELLS_FIRE_SHADER
import com.example.agsl_art.ui.shader.PERLIN_NOISE

@OptIn(ExperimentalTextApi::class)
@Composable
@Preview
fun FireUI() {
    val time by produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it / 5000f
            }
        }
    }

    val shader = RuntimeShader(CELLS_FIRE_SHADER)
    Box(
        modifier = Modifier
            .padding(10.dp)
//            .fillMaxSize()
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
//            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Click me!")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
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
        }
    }
}