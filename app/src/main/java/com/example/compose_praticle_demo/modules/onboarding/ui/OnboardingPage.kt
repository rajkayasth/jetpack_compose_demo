package com.example.compose_praticle_demo.modules.onboarding.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose_praticle_demo.modules.onboarding.models.OnboardingModel
import com.example.compose_praticle_demo.modules.onboarding.viewmodel.OnboardingViewmodel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope

@Composable
fun OnboardingPage(
    navController: NavController?,
    viewModel: OnboardingViewmodel,
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false // or true depending on background

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent, darkIcons = useDarkIcons
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        val pagerState = rememberPagerState(pageCount = { viewModel.onboardingPages.size })
        val currentPage = viewModel.currentPage.collectAsState()
        val coroutineScope = rememberCoroutineScope()


        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage } // Use currentPage if you're on older versions
                .collect { page ->
                    viewModel.currentPage.value = page
                }
        }



        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            HorizontalPager(
                state = pagerState, modifier = Modifier.weight(1f),

                ) { page ->
                OnboardingPageView(
                    onboardingItem = viewModel.onboardingPages[page],
                    pagerState = pagerState,
                    viewModel = viewModel,
                    coroutineScope = coroutineScope,
                )

            }



            Box(
                modifier = Modifier
                    .background(
                        color = Color.White
                    )
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                when (currentPage.value) {
                    0 -> {
                        CenteredRow {
                            IconButtonBlackForward(onClick = {
                                viewModel.goToNextPage(
                                    coroutineScope = coroutineScope, pagerState = pagerState
                                )
                            })
                        }
                    }

                    1 -> {
                        CenteredRow {
                            IconButtonOutlinedBack(onClick = {
                                viewModel.goToPreviousPage(
                                    coroutineScope = coroutineScope, pagerState = pagerState
                                )
                            })
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButtonBlackForward(onClick = {
                                viewModel.goToNextPage(
                                    coroutineScope = coroutineScope, pagerState = pagerState
                                )
                            })
                        }
                    }

                    2 -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            FullWidthBlackButton(text = "LOG IN", onClick = {

                            })
                            Spacer(modifier = Modifier.width(16.dp))
                            FullWidthOutlinedButton(text = "SIGN UP", onClick = {

                            })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }


        }
    }
}


@Composable
fun OnboardingAppBar(
    currentPage: Int,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 30.dp
            )
            .background(Color.Transparent) // Set background to transparent
            .padding(horizontal = 16.dp)
    ) {
        when (currentPage) {
            0 -> {
                // Page 1: Show Skip and Back button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                    TextButton(onClick = onSkipClick) {
                        Text("SKIP", color = Color.White)
                    }
                }
            }
        }
    }
}


@Composable
fun CenteredRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        content = content
    )
}

@Composable
fun IconButtonBlackForward(onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(44.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        shape = RoundedCornerShape(0.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Next",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun IconButtonOutlinedBack(onClick: () -> Unit = {}) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.size(44.dp),
        shape = RoundedCornerShape(0.dp),
        border = BorderStroke(1.dp, Color.Black),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun RowScope.FullWidthBlackButton(text: String, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier.weight(1f)
    ) {
        Text(text = text, color = Color.White)
    }
}

@Composable
fun RowScope.FullWidthOutlinedButton(text: String, onClick: () -> Unit = {}) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.weight(1f)
    ) {
        Text(text = text, color = Color.Black)
    }
}


@Composable
fun OnboardingPageView(
    onboardingItem: OnboardingModel,
    pagerState: PagerState,
    viewModel: OnboardingViewmodel,
    coroutineScope: CoroutineScope,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top image
            Image(
                painter = painterResource(id = onboardingItem.imageRes),
                contentDescription = onboardingItem.imageRes.toString(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 1.5f)
            )

            // Bottom content takes remaining space
            Column(
                modifier = Modifier
                    .fillMaxSize()// fills remaining space
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                CustomPagerIndicator(
                    currentPage = pagerState.currentPage, pageCount = viewModel.onboardingPages.size
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = onboardingItem.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = onboardingItem.description,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        // App bar overlayed on the image (top of the screen)
        OnboardingAppBar(
            currentPage = pagerState.currentPage,
            onBackClick = { /* Handle Back */ },
            onSkipClick = {
                viewModel.goToNextPage(coroutineScope, pagerState)
            },
            onNextClick = { /* Handle Next */ },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart) // ensures it's over the image
        )
    }
}


@Composable
fun CustomPagerIndicator(
    currentPage: Int,
    pageCount: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.Black,
    inactiveColor: Color = Color.LightGray,
) {
    Row(
        horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth()
    ) {
        (0 until pageCount).forEach { index ->
            val isSelected = index == currentPage
            Box(
                modifier = Modifier
                    .size(width = if (isSelected) 18.dp else 6.dp, height = 6.dp)
                    .background(
                        color = if (isSelected) activeColor else inactiveColor,
                        shape = if (isSelected) RoundedCornerShape(3.dp) else CircleShape
                    )
            )

            // Add spacer except after the last item
            if (index < pageCount - 1) {
                Spacer(modifier = Modifier.width(6.dp))
            }
        }
    }
}
