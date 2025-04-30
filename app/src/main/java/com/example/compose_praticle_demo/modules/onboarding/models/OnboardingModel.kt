package com.example.compose_praticle_demo.modules.onboarding.models

data class OnboardingModel(
    val title: String,
    val description: String,
    val isAppBarBackVisible : Boolean,
    val isBackVisible : Boolean,
    val isForwardVisible : Boolean,
    val isSkipTitleVisible : Boolean,
    val imageRes: Int // e.g. R.drawable.onboarding1
)
