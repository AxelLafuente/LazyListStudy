import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mystudy.ui.theme.PrimaruBlue
import com.example.mystudy.ui.theme.SecondaryBlue
import com.example.mystudy.ui.theme.TextBlack
import com.example.mystudy.ui.theme.WhiteBackground

@Composable
fun RadialGradientScaffold(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(PrimaruBlue, SecondaryBlue),
    radiusMultiplier: Float = 1.2f,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    snackbarHost: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    val gradient = remember(size, colors) {
        if (size.height > 0) {
            val center = Offset(
                x = size.width / 10f,
                y = size.height.toFloat()
            )
            Brush.radialGradient(
                colors = colors,
                center = center,
                radius = size.height * radiusMultiplier
            )
        } else null
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = topBar ?: {},
        bottomBar = bottomBar ?: {},
        floatingActionButton = floatingActionButton ?: {},
        snackbarHost = snackbarHost ?: {}
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .onSizeChanged { size = it }
                .background(WhiteBackground)
                .padding(paddingValues)
        ) {
            content(paddingValues)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRadialGradientScaffold() {
    RadialGradientScaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Preview da Tela", color = TextBlack, fontSize = 24.sp)
        }
    }
}