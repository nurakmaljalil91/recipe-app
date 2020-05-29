package com.nurakmaljalil91.recipeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.squareup.picasso.Picasso
import java.io.Serializable
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import org.json.JSONArray

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var imgView: ImageView
    private lateinit var titleView:TextView
    private lateinit var ingredietListView:ListView
    private lateinit var toolbar: Toolbar
    private lateinit var labelView:TextView


    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"
        const val EXTRA_IMG = "image"
        const val EXTRA_SERVING = "servings"
        const val EXTRA_INGREDIENTS = "ingredientLines"
        const val EXTRA_LABEL = "label"

        private val LABEL_COLORS = hashMapOf(
            "Low-Carb" to R.color.colorLowCarb,
            "Low-Fat" to R.color.colorLowFat,
            "Low-Sodium" to R.color.colorLowSodium,
            "Medium-Carb" to R.color.colorMediumCarb,
            "Vegetarian" to R.color.colorVegetarian,
            "Balanced" to R.color.colorBalanced
        )

        fun newIntent(context: Context, recipe: Recipe): Intent {
            val detailIntent = Intent(context, RecipeDetailActivity::class.java)
            val bundle = Bundle()
            detailIntent.putExtra(EXTRA_TITLE, recipe.title)
            detailIntent.putExtra(EXTRA_URL, recipe.instructionUrl)
            detailIntent.putExtra(EXTRA_IMG, recipe.imageUrl)
            detailIntent.putExtra(EXTRA_SERVING,recipe.serving)
            val list = Array(recipe.ingredients.length()){
                recipe.ingredients.getString(it)
            }

            detailIntent.putExtra(EXTRA_INGREDIENTS,list)
            detailIntent.putExtra(EXTRA_LABEL,recipe.label)


            return detailIntent
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val title = intent.extras.getString(EXTRA_TITLE)
        val url = intent.extras.getString(EXTRA_URL)
        val img = intent.extras.getString(EXTRA_IMG)
        val serving = intent.extras.getInt(EXTRA_SERVING)
        val ingredients = intent.extras.getStringArray(EXTRA_INGREDIENTS)
        val label = intent.extras.getString(EXTRA_LABEL)

        setTitle(title)

        //webView = findViewById(R.id.detail_web_view)
        imgView = findViewById(R.id.detail_img_view)
        titleView = findViewById(R.id.titleView)
        ingredietListView = findViewById(R.id.ingredientsList)
        labelView = findViewById(R.id.ingLabelView)


        val arrayAdapter = IngrediantAdapter(this, ingredients)
        ingredietListView.adapter = arrayAdapter
        titleView.text = "Serving for $serving"
        labelView.setText(label)

        val titleTypeFace = ResourcesCompat.getFont(this, R.font.josefinsans_bold)
        titleView.typeface = titleTypeFace
        labelView.typeface = titleTypeFace
        Picasso.with(this).load(img).placeholder(R.mipmap.ic_launcher).into(imgView)
    
        toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        labelView.setTextColor(ContextCompat.getColor(this, RecipeDetailActivity.LABEL_COLORS[label] ?: R.color.colorPrimary))

       // webView.loadUrl(url)
    }
}
