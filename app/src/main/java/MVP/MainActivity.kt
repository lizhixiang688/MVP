package MVP

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import MVVM.MainActivity2
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.Data
import com.example.kotlinapp.DataAdapter
import com.example.kotlinapp.R
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity(), IView {


    private var datalist = ArrayList<Data>()
    val adapter=DataAdapter(datalist)
    val presnter: IPresenter = IPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler: RecyclerView = findViewById(R.id.recycler)
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager=layoutManager
        recycler.adapter=adapter

    }

    fun next(view: View) {
        presnter.request("https://www.wanandroid.com/friend/json")
    }

    override fun updateUi(text: String) {
        val gson=Gson()
        try {
            val jsonObject =JSONObject(text)
            val jsonArray=jsonObject.getJSONArray("data")

          /*                 我想用GSON ，，还是太菜了，不会用 emm
            val typeOf=object :TypeToken<ArrayList<Data>>(){}.type
          for (i in 0 until jsonArray.length()) {
                  val user:JsonElement= JsonParser().parse(gson.toJson(jsonArray.getJSONObject(i)))
                   val data=gson.fromJson<ArrayList<Data>>(user,typeOf)
                datalist=data
            }*/

            for (i in 0 until jsonArray.length()){
                val jsonObjecti=jsonArray.getJSONObject(i)
                val name=jsonObjecti.getString("name")
                val link=jsonObjecti.getString("link")
                var data=Data(name,link)
                datalist.add(data)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
       adapter.notifyDataSetChanged()
    }
}