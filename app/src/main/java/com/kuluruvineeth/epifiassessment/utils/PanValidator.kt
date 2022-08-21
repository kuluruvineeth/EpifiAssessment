package com.kuluruvineeth.epifiassessment.utils

import com.kuluruvineeth.epifiassessment.utils.AppConstants.PAN_REGEX
import java.util.regex.Matcher
import java.util.regex.Pattern

class PanValidator {

    fun validatePan(panData: String): Boolean{
        val pattern: Pattern = Pattern.compile(PAN_REGEX)
        val matcher: Matcher = pattern.matcher(panData)
        if(matcher.matches()){
            return true
        }
        return false
    }
}