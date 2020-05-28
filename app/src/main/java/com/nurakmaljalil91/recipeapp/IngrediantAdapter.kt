package com.nurakmaljalil91.recipeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.EditText
import javax.sql.CommonDataSource

class IngrediantAdapter (private val context: Context, private val dataSource: Array<String>):BaseAdapter(){
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_ingredients,p2,false)


        val editText = rowView.findViewById<EditText>(R.id.ingredientText) as EditText
        val ingredient = getItem(p0) as String

        editText.setText(ingredient)


        return rowView
    }


    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}