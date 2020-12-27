package com.example.myapplication

class ProgramExpert {
    fun getLanguage(feature: String) =
        when(feature){
            "快速" -> "c"
            "容易" -> "python"
            "新语言" -> "kotlin"
            "面向对象" -> "java"
            else -> "无"
        }
}