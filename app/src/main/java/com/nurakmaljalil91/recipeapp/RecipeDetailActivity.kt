package com.nurakmaljalil91.recipeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.squareup.picasso.Picasso
import java.io.Serializable

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var imgView: ImageView
    private lateinit var titleView:TextView

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"
        const val EXTRA_IMG = "image"
        const val EXTRA_SERVING = "servings"
        const val EXTRA_INGREDIENTS = "ingredientLines"

        fun newIntent(context: Context, recipe: Recipe): Intent {
            val detailIntent = Intent(context, RecipeDetailActivity::class.java)
            val bundle = Bundle()
            detailIntent.putExtra(EXTRA_TITLE, recipe.title)
            detailIntent.putExtra(EXTRA_URL, recipe.instructionUrl)
            detailIntent.putExtra(EXTRA_IMG, recipe.imageUrl)
            detailIntent.putExtra(EXTRA_SERVING,recipe.serving)
            bundle.putStringArrayList(EXTRA_INGREDIENTS, recipe.ingredients)
            detailIntent.putExtras(bundle)


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
        val ingredients = intent.extras.getStringArrayList(EXTRA_INGREDIENTS)

        setTitle(title)

        //webView = findViewById(R.id.detail_web_view)
        imgView = findViewById(R.id.detail_img_view)
        titleView = findViewById(R.id.titleView)

        titleView.text = ingredients[0]

        val titleTypeFace = ResourcesCompat.getFont(this, R.font.josefinsans_bold)
        titleView.typeface = titleTypeFace

        Picasso.with(this).load(img).placeholder(R.mipmap.ic_launcher).into(imgView)

       // webView.loadUrl(url)
    }
}
