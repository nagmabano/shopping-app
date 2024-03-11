package com.nagma.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.nagma.myapplication.data.Product
import com.nagma.myapplication.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityProductBinding
    private val onItemClick:(Product) -> Unit = { product ->
        Log.i("selectedItem", "selected item is $product")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<MainViewModel>()
        viewModel.products.observe(this) { products ->
            binding.productList.adapter = ProductAdapter(products, onItemClick)
        }



        setSupportActionBar(binding.toolbar)
    }
}