package com.example.haberaksuygulamas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    var searchView: SearchView? = null
    var nestedScroolView: NestedScrollView? = null
    var recylerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    var bottom_nav: BottomNavigationView? = null

    var newsList = ArrayList<NewsInfo>()
    var myAdapter = NewsAdapter(this@MainActivity, newsList)
    var page = 1
    var limit = 10

    var queryText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.searchView)
        nestedScroolView = findViewById(R.id.nested_scrool_view)
        recylerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)

        progressBar?.visibility = View.GONE
       /* bottom_nav = findViewById(R.id.bottom_navigation)
        bottom_nav?.selectedItemId = R.id.home

        bottom_nav?.setOnNavigationItemReselectedListener(BottomNavigationView.OnNavigationItemReselectedListener OnNavigationItemSelectedListener@{
                item ->
            when(item.itemId){
                R.id.home -> {
                    return@OnNavigationItemSelectedListener
                }
                R.id.favoritesPage -> {
                    val intent = Intent(this,FavoriteNews::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener
                }
            }
        })*/

        myAdapter = NewsAdapter(this@MainActivity, newsList)
        var lm = LinearLayoutManager(this)
        recylerView?.layoutManager = lm
        recylerView?.adapter = myAdapter

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                queryText = query!!
                getData(page, limit, query!!)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newsList.clear()
                queryText = newText!!

                return false
            }

        })

        recylerView?.addOnItemTouchListener(RecyclerItemClickListener(this, recylerView!!, object : RecyclerItemClickListener.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
               var intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("newsDetails", newsList.get(position))
                startActivity(intent)
            }
            override fun onItemLongClick(view: View?, position: Int) {
            }
        }))


        nestedScroolView?.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                page++

                progressBar?.visibility = View.VISIBLE

                getData(page, limit, queryText)
            }
        })

    }

    private fun getData(page: Int, limit: Int, query: String) {

        val apiKey = "c82ed2f8175747a893c9013e4be0b4eb"

        ApiClient.getApiService().STRING_CALL(query, page, limit, apiKey).enqueue(object : retrofit2.Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {

                print("HATA  -------------> " + t.message.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body() != null) {
                    progressBar?.visibility = View.GONE

                    val Jasonobject = JSONObject(response.body())
                    val jsonArray = Jasonobject.getJSONArray("articles")

                    Log.d("JSON ", jsonArray.toString())

                    parseResult(jsonArray)
                }
            }

        })

    }

    private fun parseResult(jsonArray: JSONArray) {


        for (i in 0 until jsonArray.length() - 1) {

            try {

                var jsonObject = jsonArray.getJSONObject(i)

                var title = jsonObject.getString("title")
                var author = jsonObject.getString("author")
                var description = jsonObject.getString("description")
                var url = jsonObject.getString("url")
                var imageUrl = jsonObject.getString("urlToImage")
                var publishedAt = jsonObject.getString("publishedAt")
                var content = jsonObject.getString("content")

                var news_data = NewsInfo(title, author, description, url, imageUrl, publishedAt, content)
                newsList.add(news_data)

            } catch (e: Exception) {

                Log.e("Exception" , e.message.toString())
            }

        }

        Log.d("List Uzunluğu " , newsList.size.toString())

        myAdapter = NewsAdapter(this@MainActivity, newsList)
        recylerView?.adapter = myAdapter


    }
}

