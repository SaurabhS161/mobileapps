package com.example.petsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider;
import com.example.petsapp.models.PetModelClass
import com.example.petsapp.models.PetsList


class MainActivity : AppCompatActivity() {
    var context: MainActivity? = null
    var viewModel: MainViewModel? = null
    var recyclerViewAdapter = RecyclerViewAdapter(ArrayList())
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = recyclerViewAdapter

        observeViewModel()
        viewModel?.fetchPets(this)
    }

    private fun observeViewModel() {
        viewModel?.pets?.observe(this) { it ->
            recyclerViewAdapter.loadData(it)
        }
    }

}