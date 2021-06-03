package com.example.myapplication.code

import com.example.myapplication.config.Configuration.Companion.PASSWORD_LENGTH

class CodeChecker {

    companion object {
        const val NON_VALID_MSG = "Incorrect code!"
        const val VALID_MSG = "Correct code!"
        var codeToCheck = ""
    }

    init {
        refreshCode()
    }

    fun isCodeCorrect(text: CharSequence): Boolean {
        return text.toString() == (codeToCheck)
    }

    fun refreshCode() {
        var pass = ""
        for (i in 1..PASSWORD_LENGTH) {
            pass += (0..9).random()
        }
        codeToCheck = pass
    }
}