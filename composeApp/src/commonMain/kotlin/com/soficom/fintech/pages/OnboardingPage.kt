package com.soficom.fintech.pages

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import fintech.composeapp.generated.resources.*
import org.jetbrains.compose.resources.*
import kotlin.math.*

@Composable
fun OnboardingPage(){
    OnboardingBody()
}

@Composable
fun OnboardingBody(){
    val transition = rememberInfiniteTransition()

    val offsetY by transition.animateFloat(
        initialValue = 0f,
        targetValue = -20f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Column(modifier = Modifier.padding(5.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            Image(painterResource(Res.drawable.money), null, modifier = Modifier.size(380.dp).graphicsLayer {
                translationY = offsetY
            }.absoluteOffset(y = 52.dp))
            Image(painterResource(Res.drawable.soficom), null, modifier = Modifier.size(150.dp))
//            GrowthGraph(modifier = Modifier.size(120.dp))
            Column(Modifier.absoluteOffset(y = (-51).dp)) {
                Text(stringResource(Res.string.sofi_slog), fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(20.dp))
                Text(stringResource(Res.string.sofi_send), fontSize = 20.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)


            }
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color.Black)){
                Text(stringResource(Res.string.sofi_btn_next), color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

    }

}

@Composable
@Preview(showBackground = true)
fun OnboardingPreview(){
    OnboardingBody()
}

@Composable
fun GrowthGraph(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {

        val width = size.width
        val height = size.height

        // 🎨 blocs derrière (semi transparents)
        drawRoundRect(
            color = Color(0x33FF8A80),
            topLeft = Offset(width * 0.2f, height * 0.3f),
            size = Size(width * 0.25f, height * 0.5f),
            cornerRadius = CornerRadius(20f, 20f)
        )

        drawRoundRect(
            color = Color(0x33FFF59D),
            topLeft = Offset(width * 0.5f, height * 0.15f),
            size = Size(width * 0.25f, height * 0.65f),
            cornerRadius = CornerRadius(20f, 20f)
        )

        // 📈 zigzag
        val path = Path().apply {
            moveTo(width * 0.1f, height * 0.8f)
            lineTo(width * 0.3f, height * 0.6f)
            lineTo(width * 0.4f, height * 0.65f)
            lineTo(width * 0.6f, height * 0.4f)
            lineTo(width * 0.75f, height * 0.45f)
            lineTo(width * 0.9f, height * 0.2f)
        }

        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(width = 8f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        val end = Offset(width * 0.9f, height * 0.2f)
        val beforeEnd = Offset(width * 0.75f, height * 0.45f)

        val angle = atan2(
            end.y - beforeEnd.y,
            end.x - beforeEnd.x
        )

        val arrowLength = 30f
        val degrees = 25.0
        val arrowAngle = degrees * (PI / 180.0)

        val p1 = Offset(
            end.x - arrowLength * cos(angle - arrowAngle).toFloat(),
            end.y - arrowLength * sin(angle - arrowAngle).toFloat()
        )

        val p2 = Offset(
            end.x - arrowLength * cos(angle + arrowAngle).toFloat(),
            end.y - arrowLength * sin(angle + arrowAngle).toFloat()
        )

        drawLine(Color.Black, end, p1, strokeWidth = 8f, cap = StrokeCap.Round)
        drawLine(Color.Black, end, p2, strokeWidth = 8f, cap = StrokeCap.Round)
    }
}