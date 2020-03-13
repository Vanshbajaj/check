package com.example.myapp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ModelAdapter internal constructor(private val item_list: ArrayList<Model>, private val mainActivity: MainActivity) : RecyclerView.Adapter<ModelAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rowitem, null)
        // create ViewHolder
        return ViewHolder(view, mainActivity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = position
        holder.item_name.text = item_list[position].itemName
        //
//        holder.chkSelected.setChecked(item_list.get(position).isSelected());
//
//        holder.chkSelected.setTag(item_list.get(position));
//
//
        if (!mainActivity.isactionmode) {
            holder.chkSelected.visibility = View.GONE
        } else {
            holder.chkSelected.visibility = View.VISIBLE
            holder.chkSelected.isChecked = false
        }
    }

    override fun getItemCount(): Int {
        return item_list.size
    }

    private fun deleteItemFromList(v: View, position: Int) {
        val builder = AlertDialog.Builder(v.context)
        //builder.setTitle("Dlete ");
        builder.setMessage("Delete Item ?")
                .setCancelable(false)
                .setPositiveButton("CONFIRM"
                ) { dialog, id ->
                    item_list.removeAt(position)
                    notifyDataSetChanged()
                }
                .setNegativeButton("CANCEL") { dialog, id -> }
        builder.show()
    }

    inner class ViewHolder(itemLayoutView: View, activity: MainActivity?) : RecyclerView.ViewHolder(itemLayoutView), View.OnClickListener {
        var item_name: TextView
        var btn_delete: ImageButton
        var chkSelected: CheckBox
        var view: View
        override fun onClick(v: View) {
            mainActivity.Makepostion(v, adapterPosition)
        }

        init {
            item_name = itemLayoutView.findViewById<View>(R.id.txt_Name) as TextView
            btn_delete = itemLayoutView.findViewById<View>(R.id.btn_delete_unit) as ImageButton
            chkSelected = itemLayoutView.findViewById<View>(R.id.chk_selected) as CheckBox
            view = itemLayoutView
            view.setOnLongClickListener(activity)
            chkSelected.setOnClickListener(this)
        }
    }

    fun Removeitem(selected: ArrayList<Model>) {
        for (i in selected.indices) {
            item_list.remove(selected[i])
            notifyDataSetChanged()
        }
    }

}