package lat.c14210223.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

data class wayang(
    var foto : String,
    var nama : String,
    var karakter : String,
    var deskripsi : String
)

class adapterRecView (private val listWayang: ArrayList<wayang>) : RecyclerView.Adapter<adapterRecView.ListViewHolder>(){
 inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     var _namaWayang = itemView.findViewById<TextView>(R.id.namaWayang)
     var _karakterWayang = itemView.findViewById<TextView>(R.id.karakterWayang)
     var _deskripsiWayang = itemView.findViewById<TextView>(R.id.deskripsiWayang)
     var _gambarWayang = itemView.findViewById<TextView>(R.id.gambarWayang)
 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}