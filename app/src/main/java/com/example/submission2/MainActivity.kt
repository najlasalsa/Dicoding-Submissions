package com.example.submission2

import android.app.SearchManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.apply {
            rvGitHub.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGitHub.setHasFixedSize(true)
        }
        viewModel.listUser.observe(this) { listUser ->
            binding.rvGitHub.adapter = UserAdapter(listUser)
        }
        viewModel.isLoad.observe(this) {
            showLoad(it)
        }
        viewModel.error.observe(this) {
            it.getContentIfNotHandled()?.let { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        binding.srcView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    val getUser = viewModel.setSearchResponse(query)
                    clearFocus()
                    if (query.isEmpty() || getUser.equals(null)) {
                        binding.rvGitHub.adapter = UserAdapter(emptyList())
                    } else {
                        viewModel.setSearchResponse(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
        viewModel.error.observe(this) {
            it.getContentIfNotHandled()?.let { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoad(isLoad: Boolean) {
        binding.progressBar.visibility = if (isLoad) View.VISIBLE else View.INVISIBLE
    }
}