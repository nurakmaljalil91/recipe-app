package com.nurakmaljalil91.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.recipe_list_view)

        val recipeList = Recipe.getRecipesFromFile("recipes.json",this)


        val adapter = RecipeAdapter(this, recipeList)
        listView.adapter = adapter

        val context = this
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRecipe = recipeList[position]

            val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)

            startActivity(detailIntent)
        }

        toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

    }

    //override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //super.onCreateOptionsMenu(menu)
       // val menuInflater = MenuInflater(this).inflate(R.menu.main_menu, menu)
       // return true
    //}
}
