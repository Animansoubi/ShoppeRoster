package ani.man.shopperoster.intro

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ani.man.shopperoster.R
import ani.man.shopperoster.ui.theme.ShoppeRosterTheme

@Composable
fun IntroScreen() {
    val introPages = remember {
        listOf(
            IntroPageModel(
                R.drawable.intro_img_1,
                "Shop smart and shop easy with Shoppe Roster"
            ),
            IntroPageModel(
                R.drawable.intro_img_2,
                "Our perfect shopping companion, every trip!"
            ),
            IntroPageModel(
                R.drawable.intro_img_3,
                "Stay on top of your shopping game!"
            ),
        )
    }
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { introPages.size })
    IntroScreenDetail(
        pagerState = pagerState,
        introPages = introPages,
        onViewEvent = {}
    )
}

@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    ShoppeRosterTheme {
        IntroScreen()
    }
}

@Composable
fun IntroScreenDetail(
    pagerState: PagerState,
    introPages: List<IntroPageModel>,
    onViewEvent: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            val introPage = introPages[page]
            val imageResource = introPages[page]
            ShowImageAndDescription(imageResource = imageResource, introPage = introPage)
        }
        PageIndicator(introPages, pagerState)
        CustomActionButton(
            text = "Get Started",
            onClicked = { onViewEvent() }
        )
        ShowSignInText { onViewEvent() }
    }
}


@Composable
fun ShowImageAndDescription(
    imageResource: IntroPageModel,
    introPage: IntroPageModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(imageResource.imageResId),
            contentDescription = introPage.description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = introPage.description,
            modifier = Modifier
                .padding(16.dp),
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            ),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(
    introPages: List<IntroPageModel>,
    pagerState: PagerState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        introPages.forEachIndexed { index, _ ->
            val isSelected = index == pagerState.currentPage
            PageIndicatorView(
                isSelected = isSelected,
                selectedColor = Color.Black,
                defaultColor = Color.Gray,
                defaultRadius = 15.dp,
                selectedLength = 15.dp,
                animationDurationInMillis = 300,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun PageIndicatorView(
    isSelected: Boolean,
    selectedColor: Color,
    defaultColor: Color,
    defaultRadius: Dp,
    selectedLength: Dp,
    animationDurationInMillis: Int,
    modifier: Modifier = Modifier,
) {
    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            selectedColor
        } else {
            defaultColor
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        ), label = ""
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            selectedLength
        } else {
            defaultRadius
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        ), label = ""
    )
    Canvas(
        modifier = modifier
            .size(
                width = width,
                height = defaultRadius,
            ),
    ) {
        drawRoundRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(
                width = width.toPx(),
                height = defaultRadius.toPx(),
            ),
            cornerRadius = CornerRadius(
                x = defaultRadius.toPx(),
                y = defaultRadius.toPx(),
            ),
        )
    }
}

@Composable
fun CustomActionButton(
    text: String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            shape = RoundedCornerShape(0.dp),
            onClick = onClicked,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            contentPadding = PaddingValues(8.dp),
            content = {
                Text(
                    text = text,
                    modifier = Modifier.padding(4.dp),
                )
            }
        )
    }
}

@Composable
fun ShowSignInText(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .clickable {
                    onClick()
                },
            text = "Already have an account? Sign in"
        )
    }
}