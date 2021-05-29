package com.example.myapplication.utils

class CodeGenUtility {
    companion object{
        var CURRENT_PASS = "1234"
        fun genNewPassword(): CharSequence {
//            val pass: String = "" //you don't have to write that this is String, Kotlin will figure this out. 'var pass = ""' should work fine.
//            for (i in 1..4)
//                val pass = pass + (0..9).random() //it is clearer to use "pass += value" instead of "pass = pass + value"
            // TODO: Why the above does not work?!
            // val is used for variables which won't be reassigned. If you want to assign new value to variable after initializing it you should
            // use 'var' - above code should work if you switch val for var in line 7 and won't use var or val in line 9(you don't have and shouldn't
            // do this because you want to assign value to already existing variable).
            val a = (0..9).random()
            val b = (0..9).random()
            val c = (0..9).random()
            val d = (0..9).random()
            this.CURRENT_PASS = "$a$b$c$d"
            return this.CURRENT_PASS
        }
    }
}