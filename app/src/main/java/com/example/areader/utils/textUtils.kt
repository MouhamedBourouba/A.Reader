package com.example.areader.utils

import android.text.TextUtils
import android.util.Patterns

class TextUtils {

     fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


}