package com.example.compose_praticle_demo.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MyPreference @Inject constructor(private var mSharedPref: SharedPreferences) {

    var onBoardingVisited: Boolean
        get() = getValueBoolean(PrefKey.ONBOARDING_VISITED, false)
        set(flag) = setValueBoolean(PrefKey.ONBOARDING_VISITED, flag)

    var onBoardingVisitedEn: Boolean
        get() = getValueBoolean(PrefKey.ONBOARDING_VISITED+ PrefKey.EN_CODE, false)
        set(flag) = setValueBoolean(PrefKey.ONBOARDING_VISITED+ PrefKey.EN_CODE, flag)

    var onBoardingVisitedAr: Boolean
        get() = getValueBoolean(PrefKey.ONBOARDING_VISITED+ PrefKey.AR_CODE, false)
        set(flag) = setValueBoolean(PrefKey.ONBOARDING_VISITED+ PrefKey.AR_CODE, flag)

    var isLogined: Boolean
        get() = getValueBoolean(PrefKey.IS_LOGINED, false)
        set(flag) = setValueBoolean(PrefKey.IS_LOGINED, flag)

    var selectedLanguage: String
        get() = getValueString(PrefKey.SELECTED_LANGUAGE, PrefKey.AR_CODE) ?: PrefKey.AR_CODE
        set(flag) = setValueString(PrefKey.SELECTED_LANGUAGE, flag)



    fun getValueString(
        key: String,
        defaultValue: String,
    ): String? {
        return mSharedPref.getString(key, defaultValue)
    }

    fun setValueString(key: String, value: String?) {
        mSharedPref.edit {
            putString(key, value)
            apply()
        }
    }

    fun getValueBoolean(
        key: String,
        defaultValue: Boolean,
    ): Boolean {
        return mSharedPref.getBoolean(key, defaultValue)
    }

    fun setValueBoolean(key: String, value: Boolean) {
        mSharedPref.edit {
            putBoolean(key, value)
            apply()
        }
    }

    fun getValueInt(
        key: String,
        defaultValue: Int,
    ): Int {
        return mSharedPref.getInt(key, defaultValue)
    }

    fun setValueInt(key: String, value: Int) {
        mSharedPref.edit {
            putInt(key, value)
            apply()
        }
    }

    private fun clearAllData() {
        mSharedPref.edit {
            clear()
            clearAllData()
            apply()
        }
    }

}
