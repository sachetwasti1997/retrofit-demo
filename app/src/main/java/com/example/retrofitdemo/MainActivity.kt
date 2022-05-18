package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.liveData
import com.example.retrofitdemo.data.AlbumList
import com.example.retrofitdemo.network.AlbumNetwork
import com.example.retrofitdemo.network.RetrofitInstance
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val networkInstance = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumNetwork::class.java)

        val text_view = findViewById<TextView>(R.id.resultView)

        val liveAlbumData = liveData<Response<AlbumList>> {
            val res = networkInstance.getAlbums()
            emit(res)
        }

        liveAlbumData.observe(this) {
            Log.d("NETWORK", "onCreate: ${it.body()}")
            val listData = it.body()?.listIterator()
            if (listData != null){
                while (listData.hasNext()){
                    val albumItem = listData.next()
                    val result = " Album Title: ${albumItem.title}\n Album Id: ${albumItem.id}\n User Id: ${albumItem.userId}\n\n\n"
                    text_view.append(result)
                }
            }
        }
    }
}