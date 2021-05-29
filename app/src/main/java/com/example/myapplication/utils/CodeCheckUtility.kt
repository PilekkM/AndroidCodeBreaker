package com.example.myapplication.utils

class CodeCheckUtility {

    companion object{
        const val VALID_MSG = "Correct code!"

        fun isPasswordCorrect(text: CharSequence, pass_to_check : CharSequence): Boolean {
            return text.toString() == (pass_to_check)
        }
    }

}