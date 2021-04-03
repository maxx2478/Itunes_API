package com.trade.itunes_api_manohar.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Query
import androidx.room.Room
import com.trade.itunes_api_manohar.R
import com.trade.itunes_api_manohar.adapter.SearchAdapter
import com.trade.itunes_api_manohar.models.ResultModel
import com.trade.itunes_api_manohar.utils.AppDB
import com.trade.itunes_api_manohar.utils.isOnline
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    var db: AppDB? = null
    lateinit var mainViewModel: MainViewModel
    private var searchAdapter: SearchAdapter?=null
    private var recyclerView: RecyclerView?=null
    private var searchBar:SearchView?=null
    private var progressBar:ProgressBar?=null
    private var list:List<ResultModel>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()

        list = ArrayList<ResultModel>()
        db= Room.databaseBuilder(applicationContext, AppDB::class.java,"itunes").build()
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        searchBar!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (isOnline(this@MainActivity)) {
                    if (!query!!.isEmpty()) {
                        var queryx: String = query

                        queryx = queryx.replace("\\s+".toRegex(), "+")
                        mainViewModel.getData(queryx)
                        observeViewModel()
                    }
                } else {

                    if (query!!.length>0) {
                        //searchAdapter!!.searchOffline(list!!, query, true)
                        fetchDatabaseForFirstTime(query, true)
                        //searchAdapter!!.notifyDataSetChanged()
                    }


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

            layoutManager = GridLayoutManager(context, 2)
            adapter = searchAdapter

        }
    }

    private fun observeViewModel() {

        mainViewModel.resultList.observe(this, {results ->
            results.let {
                CoroutineScope(Dispatchers.IO).launch {
                    db!!.searchDao().saveResults(results!!.results!!)
                }
                recyclerView!!.visibility = View.VISIBLE
               searchAdapter!!.updateData(results!!.results!!) }

        })

        mainViewModel.loading.observe(this, {
            it.let { if (it) progressBar!!.visibility=View.VISIBLE else progressBar!!.visibility = View.GONE }
        })

    }

    fun fetchDatabaseForFirstTime(query: String, offlinesession: Boolean)
    {
        CoroutineScope(Dispatchers.IO).launch {
            list= db!!.searchDao().getAllResults()
            withContext(Dispatchers.Main)
            {
                progressBar!!.visibility = View.GONE
                recyclerView!!.visibility = View.VISIBLE
                recyclerView!!.setItemViewCacheSize(list!!.size)
                searchAdapter!!.searchOffline(list!!, query, offlinesession)

            }

        }

    }


}