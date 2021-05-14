package com.example.myapplication.utils

class CodeCheckUtility {

    companion object{
        private const val PASSWORD = "546789"
        const val NON_VALID_MSG = "Incorrect code!"
        const val VALID_MSG = "Correct code!"

        fun isPasswordCorrect(text: CharSequence): Boolean {
            return text.toString() == (PASSWORD)
        }
    }

}