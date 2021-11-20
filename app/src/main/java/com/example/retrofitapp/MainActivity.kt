package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var url = "https://jsonplaceholder.typicode.com/"
        var rbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(retrofit_Interface::class.java)

        val data = RetroFitDataItem("body", 122, "harry", 125)

        var update = rbuilder.update(4, data)
        update.enqueue(object : Callback<RetroFitDataItem?> {
            override fun onResponse(
                call: Call<RetroFitDataItem?>,
                response: Response<RetroFitDataItem?>
            ) {
                Toast.makeText(this@MainActivity, "${response.code()} update", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<RetroFitDataItem?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT).show()
            }
        })

        var del = rbuilder.delete(5)
        del.enqueue(object : Callback<Unit?> {
            override fun onResponse(call: Call<Unit?>, response: Response<Unit?>) {
                Toast.makeText(this@MainActivity, "${response.code()} del", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<Unit?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "$t fail", Toast.LENGTH_SHORT).show()
            }
        })
        val send = rbuilder.putdata(data)

        send.enqueue(object : Callback<RetroFitDataItem?> {
            override fun onResponse(
                call: Call<RetroFitDataItem?>,
                response: Response<RetroFitDataItem?>
            ) {
                Toast.makeText(this@MainActivity, "${response.code()}", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<RetroFitDataItem?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t}", Toast.LENGTH_SHORT).show()

            }
        })

        var rdata = rbuilder.getdata()
        rdata.enqueue(object : Callback<List<RetroFitDataItem>?> {
            override fun onResponse(
                call: Call<List<RetroFitDataItem>?>,
                response: Response<List<RetroFitDataItem>?>
            ) {
                var rbody = response.body()!!
                Toast.makeText(this@MainActivity, "$rbody", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<RetroFitDataItem>?>, t: Throwable) {
            }
        })

        var patch=rbuilder.patch(3,4)
        patch.enqueue(object : Callback<Unit?> {
            override fun onResponse(call: Call<Unit?>, response: Response<Unit?>) {
                Toast.makeText(this@MainActivity, "success", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Unit?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message} dg", Toast.LENGTH_SHORT).show()
            }
        })

    }
}