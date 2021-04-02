package com.trade.itunes_api_manohar.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trade.itunes_api_manohar.R
import com.trade.itunes_api_manohar.models.ResultModel
import com.trade.itunes_api_manohar.models.SearchResultModel

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>
{
    var context: Context?=null
    var searchResults: ArrayList<ResultModel>?=null

    constructor(searchResults: ArrayList<ResultModel>, context: Context)
    {
        this.context = context
        this.searchResults = searchResults

    }

    fun updateData(searchResults: List<ResultModel>) //using for everytime user clicks on searchbutton
    {
        this.searchResults!!.clear()
        this.searchResults!!.addAll(searchResults)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return  SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResults!![position])

    }

    override fun getItemCount(): Int {
        return searchResults!!.size
    }

    class SearchViewHolder(view:View):RecyclerView.ViewHolder(view)
    {

        val trackname = view.findViewById<TextView>(R.id.trackname)
        val artistname = view.findViewById<TextView>(R.id.artistname)
        val artwork = view.findViewById<ImageView>(R.id.artwork)

        fun bind(resultModel: ResultModel)
        {

            trackname.text = resultModel.trackName.toString()
            artistname.text = resultModel.artistName.toString()
            artwork.setImage(resultModel.artworkUrl)
            Log.i("text", resultModel.artistName.toString())

        }

    }
}

private fun ImageView.setImage(artworkUrl: String?)
{
  Glide.with(context).load(artworkUrl).into(this)
}
