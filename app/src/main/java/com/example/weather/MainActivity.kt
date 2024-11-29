package com.example.weather

import android.app.DownloadManager.Request
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
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
        val dateTime: TextView = findViewById(R.id.DATATIME)
        val selectedCountry: TextView = findViewById(R.id.textView)
        val town: TextView = findViewById(R.id.textView2)
        val state: TextView = findViewById(R.id.textView3)
        val temperature: TextView = findViewById(R.id.textView4)
        val humidity: TextView = findViewById(R.id.textView5)
        val pressure: TextView = findViewById(R.id.textView6)
        val feelslike: TextView = findViewById(R.id.textView7)

        binding.apply.setOnClickListener {
            getResult(input.text.toString(),dateTime, selectedCountry, town, state, temperature,humidity,pressure, feelslike)
        }
    }
    private fun getResult(name: String,dateTime: TextView, selectedCountry: TextView,town: TextView, state: TextView, temperature: TextView,humidity: TextView,pressure: TextView, feelslike: TextView ){
        val url = "http://api.weatherapi.com/v1/current.json?key=$API_KEY&q=$name&aqi=no"
        val q = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(com.android.volley.Request.Method.GET, url,
            {
                response->
                val obj = JSONObject(response)
                val temp= obj.getJSONObject("current")
                val stateWeather= temp.getJSONObject("condition")
                val loc= obj.getJSONObject("location")
                dateTime.text = loc.getString("localtime")
                selectedCountry.text = loc.getString("country")
                town.text = loc.getString("name")
                state.text = stateWeather.getString("text")
                temperature.text = temp.getString("temp_c")
                humidity.text = temp.getString("humidity")
                pressure.text = temp.getString("pressure_mb")
                feelslike.text = temp.getString("feelslike_c")
            },
            {
                Log.d("MyLog", "Volley Error: ${it.message}")
            })
        q.add(stringRequest)
    }
}