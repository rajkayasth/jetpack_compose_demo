package com.bv.ecommerce.base

import androidx.lifecycle.ViewModel
import com.example.compose_praticle_demo.utils.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class ViewModelBase @Inject constructor(
) : ViewModel() {

    @Inject
    lateinit var mPref: MyPreference

}
