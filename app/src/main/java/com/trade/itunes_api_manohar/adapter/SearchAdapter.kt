package com.trade.itunes_api_manohar.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trade.itunes_api_manohar.R
import com.trade.itunes_api_manohar.models.ResultModel

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>
{
    var context: Context?=null
    var searchResults: ArrayList<ResultModel>?=null
    var query:String?=null
    var offlineSession:Boolean?=null

    constructor(searchResults: ArrayList<ResultModel>, context: Context)
    {
        this.context = context
        this.searchResults = searchResults

    }

    fun updateData(searchResults: List<ResultModel>) //using for everytime user clicks on searchbutton
    {
        this.offlineSession = false
        this.searchResults!!.clear()
        this.searchResults!!.addAll(searchResults)
        notifyDataSetChanged()
        Toast.makeText(context, "Querying from itunes API", Toast.LENGTH_SHORT).show()

    }

    fun searchOffline(searchResults: List<ResultModel>, querystring: String, offlineSessionx: Boolean)
    {
        this.query = querystring
        this.offlineSession = offlineSessionx
        this.searchResults!!.clear()
        this.searchResults!!.addAll(searchResults)
        Toast.makeText(context, "offline data from RoomDatabase", Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return  SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        if (!offlineSession!!)
        {
            holder.bind(searchResults!![position])
        }
        else
        {

            if (searchResults!![position].artistName.contains(query!!, ignoreCase = true) || searchResults!![position].trackName.contains(query!!, ignoreCase = true))
            {

              holder.bind(searchResults!![position])

            }


        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return searchResults!!.size
    }

    class SearchViewHolder(view: View):RecyclerView.ViewHolder(view)
    {

        val trackname = view.findViewById<TextView>(R.id.trackname)
        val artistname = view.findViewById<TextView>(R.id.artistname)
        val artwork = view.findViewById<ImageView>(R.id.artwork)

        fun bind(resultModel: ResultModel)
        {


                trackname.text = resultModel.trackName
                artistname.text = resultModel.artistName
                artwork.setImage(resultModel.artworkUrl)
                Log.i("text", resultModel.artistName.toString())



        }

    }
}

private fun ImageView.setImage(artworkUrl: String?)
{
  Glide.with(context).load(artworkUrl).into(this)
}
