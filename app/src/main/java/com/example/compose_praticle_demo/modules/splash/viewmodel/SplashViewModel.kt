package com.example.compose_praticle_demo.modules.splash.viewmodel

import com.bv.ecommerce.base.ViewModelBase
import com.example.compose_praticle_demo.utils.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModelBase() {
    @Inject
    lateinit var pref: MyPreference

//    var onboardingVisitedAr: Boolean
//        get() = pref.onBoardingVisitedAr
//        set(value) {
//            pref.onBoardingVisitedAr = value
//        }
//
//    var onboardingVisitedEn: Boolean
//        get() = pref.onBoardingVisitedEn
//        set(value) {
//            pref.onBoardingVisitedEn = value
//        }
//
//    var isLogined: Boolean
//        get() = pref.isLogined
//        set(value) {
//            pref.isLogined = value
//        }
}