package com.example.myapp


import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),OnLongClickListener {
    private var recyclerView: RecyclerView? = null
    private val chk_select_all: CheckBox? = null
    private val btn_delete_all: Button? = null
    var isactionmode = false
    var toolbar: Toolbar? = null
    var textView: TextView? = null
    var counter = 0
    private val item_list:ArrayList<Model> = ArrayList()
    private var mAdapter: ModelAdapter? = null
    private val selected:ArrayList<Model> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initControls()
    }

    private fun initControls() {
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        // chk_select_all = (CheckBox) findViewById(R.id.chk_select_all);
//btn_delete_all = (Button) findViewById(R.id.btn_delete_all);
        toolbar = findViewById(R.id.toolbar)
        textView = findViewById(R.id.itemcontainer)
        item_list.add(Model("Alpha", false))
        item_list.add(Model("Beta", false))
        item_list.add(Model("Cup Cake", false))
        item_list.add(Model("Donut", false))
        item_list.add(Model("Eclair", false))
        item_list.add(Model("Froyo", false))
        item_list.add(Model("Ginger Bread", false))
        item_list.add(Model("Honycomb", false))
        item_list.add(Model("Icecream Sandwhich", false))
        item_list.add(Model("Jelly Bean", false))
        item_list.add(Model("Kitkat", false))
        item_list.add(Model("Lolly Pop", false))
        item_list.add(Model("Marsh Mallow", false))
        item_list.add(Model("Nougat", false))
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        mAdapter = ModelAdapter(item_list, this@MainActivity)
        recyclerView!!.adapter = mAdapter
        //        chk_select_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (chk_select_all.isChecked()) {
//
//                    for (Model model : item_list) {
//                        model.setSelected(true);
//                    }
//                } else {
//
//                    for (Model model : item_list) {
//                        model.setSelected(false);
//                    }
//                }
//
//                mAdapter.notifyDataSetChanged();
//            }
//        });

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.conetxtual, menu)
        return true
    }

    override fun onLongClick(v: View): Boolean {
        isactionmode = true
        toolbar!!.menu.clear()
        toolbar!!.inflateMenu(R.menu.normal)
        textView!!.text = "0 ITEM SELECTED"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar!!.setBackgroundColor(getColor(R.color.colorPrimary))
        }
        mAdapter!!.notifyDataSetChanged()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return false
    }

    fun Makepostion(v: View, adapterPosition: Int) {
        if ((v as CheckBox).isChecked) {
            selected.add(item_list[adapterPosition])
            counter++
            updatecounter()
        } else {
            selected.remove(item_list[adapterPosition])
            counter--
            updatecounter()
        }
    }

    fun updatecounter() {
        textView!!.text = counter.toString() + "Item selected"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            mAdapter!!.Removeitem(selected)
            Removecontexualmode()       }
        return true
    }


    private fun Removecontexualmode() {
        isactionmode = false
        textView!!.text = "MYAPP"
        toolbar!!.menu.clear()
        toolbar!!.inflateMenu(R.menu.normal)
        counter = 0
        selected.clear()
        mAdapter!!.notifyDataSetChanged()
        //supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }
}