package com.example.compose_praticle_demo.modules.onboarding.viewmodel

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bv.ecommerce.base.ViewModelBase
import com.example.compose_praticle_demo.modules.onboarding.models.OnboardingModel
import com.example.compose_praticle_demo.utils.MyPreference
import com.example.compose_praticle_demo.utils.rDrawable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewmodel @Inject constructor() : ViewModelBase() {
    @Inject
    lateinit var pref: MyPreference

    val currentPage = MutableStateFlow<Int>(value = 0)


    val onboardingPages = listOf(
        OnboardingModel(
            title = "LOREM IPSUM IS SIMPLY",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry dummy",
            imageRes = rDrawable.onboarding_1,
            isSkipTitleVisible = true,
            isForwardVisible = false,
            isBackVisible = true,
            isAppBarBackVisible = true
        ),
        OnboardingModel(
            title = "LOREM IPSUM IS SIMPLY",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry dummy",
            imageRes = rDrawable.onboarding_2,
            isSkipTitleVisible = true,
            isForwardVisible = false,
            isBackVisible = true,
            isAppBarBackVisible = true
        ),
        OnboardingModel(
            title = "LOREM IPSUM IS SIMPLY",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry dummy",
            imageRes = rDrawable.onboarding_3,
            isSkipTitleVisible = true,
            isForwardVisible = false,
            isBackVisible = true,
            isAppBarBackVisible = true
        )
    )

    fun goToNextPage(coroutineScope: CoroutineScope, pagerState: PagerState) {
        coroutineScope.launch {
            val nextPage = (pagerState.currentPage + 1).coerceAtMost(pagerState.pageCount - 1)
            pagerState.animateScrollToPage(nextPage)
        }
    }

    fun goToPreviousPage(coroutineScope: CoroutineScope, pagerState: PagerState) {
        coroutineScope.launch {
            val prevPage = (pagerState.currentPage - 1).coerceAtLeast(0)
            pagerState.animateScrollToPage(prevPage)
        }
    }
}
