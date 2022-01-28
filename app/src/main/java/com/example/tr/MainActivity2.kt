package com.example.tr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tr.adapters.itemadapter
import com.example.tr.data.datasource

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val locationTextView = findViewById<TextView>(R.id.location_profile)
        locationTextView.text = intent.extras?.get("LOCATION").toString()

        val datasource1 = datasource().loadItem_1()
        val datasource2 = datasource().loadItem_2()
        val recyclerView1 = findViewById<RecyclerView>(R.id.recyclerview_1)
        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerview_2)

        recyclerView1.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView2.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView1.adapter = itemadapter(this,datasource1)
        recyclerView2.adapter = itemadapter(this,datasource2)

    }
}