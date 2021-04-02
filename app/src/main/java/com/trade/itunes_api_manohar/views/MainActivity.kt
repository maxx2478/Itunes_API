package com.trade.itunes_api_manohar.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trade.itunes_api_manohar.R
import com.trade.itunes_api_manohar.adapter.SearchAdapter

class MainActivity : AppCompatActivity() {


    lateinit var mainViewModel: MainViewModel
    private var searchAdapter: SearchAdapter?=null
    private var recyclerView: RecyclerView?=null
    private var searchBar:SearchView?=null
    private var progressBar:ProgressBar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        searchBar!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (!query!!.isEmpty()) {
                    var queryx: String = query
                    queryx = queryx.replace("\\s+".toRegex(), "+")
                    mainViewModel.getData(queryx)
                    observeViewModel()
                }
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    private fun initializeView() {
        recyclerView = findViewById(R.id.recyclerView)
        searchBar = findViewById(R.id.search_bar)
        progressBar = findViewById(R.id.progressbar)
        searchAdapter = SearchAdapter(arrayListOf(), this)
        recyclerView?.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter

        }
    }

    private fun observeViewModel() {

        mainViewModel.resultList.observe(this, {results ->
            results.let {
                recyclerView!!.visibility = View.VISIBLE
               searchAdapter!!.updateData(results!!.results!!) }

        })

        mainViewModel.loading.observe(this, {
            it.let { if (it) progressBar!!.visibility=View.VISIBLE else progressBar!!.visibility = View.GONE }
        })

    }


}