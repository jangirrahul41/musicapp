package com.example.musicapp1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdaptor: MyAdatptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myRecyclerView= findViewById(R.id.recyclerView)
        myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<Mydata?> {
            override fun onResponse(call: Call<Mydata?>, response: Response<Mydata?>) {

                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {

                        myAdaptor = MyAdatptor(this@MainActivity, data.data)
                        myRecyclerView.adapter = myAdaptor
                    }
                } else {
                    Log.d("TAG: onResponse", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Mydata?>, t: Throwable) {
                Log.d("TAG: onFailure", "onFailure" + t.message)
            }

        })

    }
}