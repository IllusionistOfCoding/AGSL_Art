package com.example.agsl_art.ui.screen.jellyfish


import android.graphics.RuntimeShader
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agsl_art.ui.vector.JellyFishPaths.bubble1
import com.example.agsl_art.ui.vector.JellyFishPaths.bubble2
import com.example.agsl_art.ui.vector.JellyFishPaths.bubble3
import com.example.agsl_art.ui.vector.JellyFishPaths.bubble4
import com.example.agsl_art.ui.vector.JellyFishPaths.bubble5
import com.example.agsl_art.ui.vector.JellyFishPaths.tentacle2
import com.example.agsl_art.ui.vector.JellyFishPaths.tentacle3
import com.example.agsl_art.ui.vector.JellyFishPaths.tentacle4
import com.example.agsl_art.ui.vector.JellyFishPaths.tentacle5
import com.example.agsl_art.ui.vector.JellyFishPaths.tentaclePath
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import com.example.agsl_art.ui.component.largeRadialGradient
import com.example.agsl_art.ui.shader.PERLIN_NOISE
import com.example.agsl_art.ui.vector.JellyFishPaths.face
import com.example.agsl_art.ui.vector.JellyFishPaths.freckle1
import com.example.agsl_art.ui.vector.JellyFishPaths.freckle2
import com.example.agsl_art.ui.vector.JellyFishPaths.freckle3
import com.example.agsl_art.ui.vector.JellyFishPaths.freckle4
import com.example.agsl_art.ui.vector.JellyFishPaths.leftEye
import com.example.agsl_art.ui.vector.JellyFishPaths.mouth
import com.example.agsl_art.ui.vector.JellyFishPaths.outerJelly
import com.example.agsl_art.ui.vector.JellyFishPaths.rightEye
import com.example.agsl_art.ui.vector.JellyFishPaths.tentacle6
import com.example.agsl_art.ui.vector.JellyFishPaths.tentacle7
import com.example.agsl_art.ui.vector.JellyFishPaths.tentacle8
import com.example.agsl_art.ui.vector.JellyFishPaths.tentacle9
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

val solidWhite = SolidColor(Color.White)

@Preview
@Composable
fun JellyfishAnimation() {
    val blinkAlphaAnimation = remember {
        Animatable(1f)
    }
    val blinkScaleAnimation = remember {
        Animatable(1f)
    }

    suspend fun instantBlinkAnimation() {
        val tweenSpec = tween<Float>(150, easing = LinearEasing)
        coroutineScope {
            launch {
                blinkAlphaAnimation.animateTo(0f, animationSpec = tweenSpec)
                blinkAlphaAnimation.animateTo(1f, animationSpec = tweenSpec)
            }
            launch {
                blinkScaleAnimation.animateTo(0.3f, animationSpec = tweenSpec)
                blinkScaleAnimation.animateTo(1f, animationSpec = tweenSpec)
            }
        }
    }

    val vectorPainterFace = rememberVectorPainter(
        defaultWidth = 530.46f.dp,
        defaultHeight = 563.1f.dp,
        viewportWidth = 530.46f,
        viewportHeight = 563.1f,
        autoMirror = true,
    ) { _, _ ->
        val duration = 3000
        val transition = rememberInfiniteTransition()
        val translationY by transition.animateFloat(
            initialValue = 0f,
            targetValue = -30f,
            animationSpec = infiniteRepeatable(
                tween(duration, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Group(name = "bubbles") {
            Path(
                bubble1,
                fill = solidWhite,
                fillAlpha = 0.67f
            )

            Path(
                bubble2,
                fill = solidWhite,
                fillAlpha = 0.75f
            )

            Path(
                bubble3,
                fill = solidWhite,
                fillAlpha = 0.89f
            )

            Path(
                bubble4,
                fill = solidWhite,
                fillAlpha = 0.77f
            )

            Path(
                bubble5,
                fill = solidWhite,
                fillAlpha = 0.77f
            )
        }
        Group(name = "face", translationY = translationY) {
            Group(
                name = "eye-left",
                scaleY = blinkScaleAnimation.value,
                pivotY = 233f
            ) {
                Path(
                    leftEye,
                    fill = SolidColor(Color(0xFFb4bebf)),
                    fillAlpha = blinkAlphaAnimation.value
                )
            }
            Group(
                name = "right-eye",
                scaleY = blinkScaleAnimation.value,
                pivotY = 233f
            ) {
                Path(
                    rightEye,
                    fill = SolidColor(Color(0xFFb4bebf)),
                    fillAlpha = blinkAlphaAnimation.value
                )
            }

            Path(
                mouth,
                fill = SolidColor(Color(0xFFd3d3d3)),
                fillAlpha = 0.72f
            )
        }
    }

    val vectorPainter = rememberVectorPainter(
        defaultWidth = 530.46f.dp,
        defaultHeight = 563.1f.dp,
        viewportHeight = 563.1f,
        viewportWidth = 530.46f,
        autoMirror = true,
    ) { _, _ ->
        val duration = 3000
        val transition = rememberInfiniteTransition()
        val translationY by transition.animateFloat(
            initialValue = 0f,
            targetValue = -30f,
            animationSpec = infiniteRepeatable(
                tween(duration, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        Group(name = "jellyfish", translationY = translationY) {
            Group(name = "tentacles") {
                Path(
                    pathData = tentaclePath,
                    fill = solidWhite,
                    fillAlpha = 0.49f
                )
                Path(
                    tentacle2,
                    fill = solidWhite,
                    fillAlpha = 0.66f
                )
                Path(
                    tentacle3,
                    fill = solidWhite,
                    fillAlpha = 0.45f
                )
                Path(
                    tentacle4,
                    fill = solidWhite,
                    fillAlpha = 0.6f
                )
                Path(
                    tentacle5,
                    fill = solidWhite,
                    fillAlpha = 1f
                )
                Path(
                    tentacle6,
                    fill = solidWhite,
                    fillAlpha = 1f
                )
                Path(
                    tentacle7,
                    fill = solidWhite,
                    fillAlpha = 1f
                )
                Path(
                    tentacle8,
                    fill = solidWhite,
                    fillAlpha = 1f
                )
                Path(
                    tentacle9,
                    fill = solidWhite
                )
            }
            Group(name = "body") {
                Path(
                    face,
                    fill = solidWhite
                )
                Path(
                    outerJelly,
                    fill = solidWhite,
                    fillAlpha = 0.5f
                )
            }
            Group(name = "freckles") {
                Path(
                    freckle1,
                    fill = SolidColor(Color(0xfff0dfe2)),
                )
                Path(
                    freckle2,
                    fill = SolidColor(Color(0xfff0dfe2)),
                )
                Path(
                    freckle3,
                    fill = SolidColor(Color(0xfff0dfe2)),
                )
                Path(
                    freckle4,
                    fill = SolidColor(Color(0xfff0dfe2)),
                )

            }
        }
    }

    val time by produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it.toFloat() / 1000f
            }
        }
    }

    val shader = RuntimeShader(PERLIN_NOISE)

    Image(
        vectorPainter, contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
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
    )
    val coroutineScope = rememberCoroutineScope()
    Image(
        vectorPainterFace, contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    coroutineScope.launch {
                        instantBlinkAnimation()
                    }
                }
            }
    )
}