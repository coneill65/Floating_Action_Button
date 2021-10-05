package com.example.floatingactionbutton

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    var listItems = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null
    lateinit var listView: ListView
    lateinit var fab: FloatingActionButton

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workBtn: Button = findViewById(R.id.collectMoneyBtn)
        val moneyTextView: TextView = findViewById(R.id.MoneyText)
        var moneyToAdd: Int
        var money = 0

        listView = findViewById(R.id.lv1)

        workBtn.setOnClickListener {
            moneyToAdd = 0
            var x = 1
            for (item in listItems){
                moneyToAdd += x.toDouble().pow(listItems.size).toInt()
                x += 1
            }
            money += moneyToAdd
            moneyTextView.text = "Money: $money\$"
        }

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listItems
        )

        listView.adapter = adapter
        fab = findViewById(R.id.FAB1)
        fab.setOnClickListener{
            val change = listItems.size.toDouble().pow(listItems.size + 2).toInt()
            if (listItems.size == 0){
                addListItem()
                Snackbar.make(it, "Upgraded", Snackbar.LENGTH_LONG).show()
            }else if (money >= change){
                money -= change
                addListItem()
                Snackbar.make(it, "Upgraded", Snackbar.LENGTH_LONG).show()
            }else{
                Snackbar.make(it, "You need ${change}\$", Snackbar.LENGTH_LONG).show()
            }
            moneyTextView.text = "Money: $money\$"
        }

    }

    private fun addListItem(){
        val listItemSize = listItems.size + 1
        listItems.add("Company $listItemSize")
        adapter?.notifyDataSetChanged()
    }
}