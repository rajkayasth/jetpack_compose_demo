package com.example.compose_praticle_demo.modules.onboarding.viewmodel

import com.bv.ecommerce.base.ViewModelBase
import com.example.compose_praticle_demo.utils.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewmodel @Inject constructor() : ViewModelBase() {
    @Inject
    lateinit var pref: MyPreference



}