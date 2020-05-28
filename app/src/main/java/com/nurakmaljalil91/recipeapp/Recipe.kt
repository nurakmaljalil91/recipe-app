package com.nurakmaljalil91.recipeapp

data class Recipe(val name:String, val age:Int) {
    override fun toString() = "$name age is $age"
}