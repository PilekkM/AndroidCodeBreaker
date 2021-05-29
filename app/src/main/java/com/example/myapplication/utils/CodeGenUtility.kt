package com.example.myapplication.utils

class CodeGenUtility {
    companion object{
        var CURRENT_PASS = "1234"
        fun genNewPassword(): CharSequence {
//            val pass: String = ""
//            for (i in 1..4)
//                val pass = pass + (0..9).random()
            // TODO: Why the above does not work?!
            val a = (0..9).random()
            val b = (0..9).random()
            val c = (0..9).random()
            val d = (0..9).random()
            this.CURRENT_PASS = "$a$b$c$d"
            return this.CURRENT_PASS
        }
    }
}