package com.example.myapplication.utils

import com.example.myapplication.config.Configuration.Companion.PASSWORD_TO_CHECK

class CodeCheckUtility {

    companion object{
        const val NON_VALID_MSG = "Incorrect code!"
        const val VALID_MSG = "Correct code!"

        fun isPasswordCorrect(text: CharSequence): Boolean {
            return text.toString() == (PASSWORD_TO_CHECK)
        }
    }

}