package com.example.coinmarketcapclone

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete

class ScrollingActivity : AppCompatActivity() {
    lateinit var originalList: List<Coins>
    lateinit var adapter: PortfolioAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        doAsync {
            val coinsViewModel = CoinsViewModel(application)
            originalList = coinsViewModel.getAllNonHoldings()
            onComplete {
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.itemAnimator = DefaultItemAnimator()
                adapter = PortfolioAdapter(originalList, applicationContext)
                recyclerView.adapter = adapter
            }
        }
        val search = findViewById<TextInputEditText>(R.id.search)

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var filteredList: List<Coins> = originalList.filter {
                    (it.coinName.contains(s!!)) || (it.coinId.contains(s))
                }
                adapter.list = filteredList
            }

        })

        val clear = findViewById<AppCompatImageView>(R.id.clear)
        clear.isClickable = true
        clear.setOnClickListener {
            search.text = null
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}


