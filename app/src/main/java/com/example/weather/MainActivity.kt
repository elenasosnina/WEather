package com.example.weather

import android.app.DownloadManager.Request
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY= "8a222ba98338405c80b153348242611"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val input = findViewById<EditText>(R.id.city)
        val but: Button = findViewById(R.id.apply)
        val dataTime: TextView = findViewById(R.id.DATATIME)
        val selectedCountry: TextView = findViewById(R.id.textView)
        val town: TextView = findViewById(R.id.textView2)
        val state: TextView = findViewById(R.id.textView3)
        val temperature: TextView = findViewById(R.id.textView4)
        val humidity: TextView = findViewById(R.id.textView5)
        val minTemp: TextView = findViewById(R.id.textView6)
        val maxTemp: TextView = findViewById(R.id.textView7)

        binding.apply.setOnClickListener {
            getResult("$input")
        }
    }
    private fun getResult(name: String){
        val url = "http://api.weatherapi.com/v1/current.json"+
        "?key=$API_KEY&q=$name&aqi=no"
        val q = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(com.android.volley.Request.Method.GET, url,
            {
                response->
                val obj = JSONObject(response)
                val temp= obj.getJSONObject("current")
                Log.d("MyLog", "Response: ${temp.getString("temp_c")}")
            },
            {
                Log.d("MyLog", "Volley Error: $it")
            })
        q.add(stringRequest)
    }
}