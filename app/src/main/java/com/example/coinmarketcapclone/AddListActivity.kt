package com.example.coinmarketcapclone

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete

class AddListActivity : AppCompatActivity() {

    lateinit var list: List<Coins>
    lateinit var adapter: AddListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchBar = findViewById<LinearLayoutCompat>(R.id.search_bar)
        val toolBar = findViewById<LinearLayoutCompat>(R.id.toolbar)
        val searchButton = findViewById<ImageView>(R.id.search_button)
        val back = findViewById<ImageView>(R.id.back)
        val searchView = findViewById<TextInputEditText>(R.id.search)
        val close = findViewById<ImageView>(R.id.close)
        searchButton.setOnClickListener {
            searchBar.visibility = View.VISIBLE
            toolBar.visibility = View.GONE
        }
        close.setOnClickListener {
            toolBar.visibility = View.VISIBLE
            searchBar.visibility = View.GONE
        }
        back.setOnClickListener {
            finish()
        }
        doAsync {
            list = CoinsViewModel(application).getAllCoins()
            onComplete {
                recyclerView.layoutManager = LinearLayoutManager(this@AddListActivity)
                adapter = AddListAdapter(this@AddListActivity,list)
                recyclerView.adapter = adapter
                searchView.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {

                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        val filteredList = list.filter {
                            it.coinName.contains(s!!.toString()) || it.coinId.contains(s.toString())
                        }
                        adapter.setList(filteredList)
                    }
                })
            }
        }
    }
}
