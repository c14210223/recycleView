package lat.c14210223.recycleview

import adapterRecView
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import wayang

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rvWayang)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        _rvWayang = findViewById<RecyclerView>(R.id.rvWayang)
        sp = getSharedPreferences("dataSP", MODE_PRIVATE)
        if(arWayang.size ==0){
            SiapkanData()
        }else{
            arWayang.forEach{
                _nama.add(it.nama)
                _gambar.add(it.foto)
                _deskripsi.add(it.deskripsi)
                _karakter.add(it.karakter)
            }
            arWayang.clear()
        }
        TambahData()
        tampilkanData()

        val gson = Gson()
        val isiSP = sp.getString("spWayang", null)
        val type = object  : TypeToken<ArrayList<wayang>> () {}.type
        if (isiSP!=null)
            arWayang = gson.fromJson(isiSP,type)
    }

    private var _nama: MutableList<String> = emptyList<String>().toMutableList()
    private var _karakter: MutableList<String> = emptyList<String>().toMutableList()
    private var _deskripsi: MutableList<String> = emptyList<String>().toMutableList()
    private var _gambar: MutableList<String> = emptyList<String>().toMutableList()


    lateinit var sp: SharedPreferences
    private var arWayang = arrayListOf<wayang>()

    private lateinit var _rvWayang: RecyclerView


    fun SiapkanData() {
        _nama = resources.getStringArray(R.array.namaWayang).toMutableList()
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang).toMutableList()
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang).toMutableList()
        _gambar = resources.getStringArray(R.array.gambarWayang).toMutableList()


    }

    fun TambahData() {

        val gson = Gson()
        val editor = sp.edit()
        arWayang.clear()
        for (position in _nama.indices) {
            val data = wayang(
                _gambar[position],
                _nama[position],
                _karakter[position],
                _deskripsi[position]
            )

            arWayang.add(data)
        }

        val json = gson.toJson(arWayang)
        editor.putString("spWayang", json)
        editor.apply()


    }

    fun tampilkanData() {
        _rvWayang.layoutManager = LinearLayoutManager(this)
        _rvWayang.adapter = adapterRecView(arWayang)
    }

}