package com.example.petsapp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider;


class MainActivity : AppCompatActivity() {
    var viewModel: MainViewModel? = null
    var recyclerViewAdapter = RecyclerViewAdapter(ArrayList())
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = recyclerViewAdapter
        recyclerViewAdapter.setOnItemClickListener(object : RecyclerViewAdapter.onItemClickListener {

            override fun onItemClick(position: Int) {
                var data = viewModel?.pets?.value?.get(position)
                startDetailsActivity(data!!.content_url)
            }
        })
        observeViewModel()
        viewModel?.fetchPets(this)
    }

    fun startDetailsActivity(url :String) {
        var intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("URL", url)
        startActivity(intent)
    }

    private fun observeViewModel() {
        viewModel?.pets?.observe(this) { it ->
            recyclerViewAdapter.loadData(it)
        }
    }

}