package com.nagma.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.nagma.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<MainViewModel>()

        binding.addButton.setOnClickListener{ view ->
            viewModel.increaseItemCount()
        }

        binding.minusButton.setOnClickListener{ view ->
            viewModel.decreaseItemCount()
        }

        binding.checkoutButton.setOnClickListener{view ->
            var result = viewModel.getPrice()
            displaySnackbar(result)
        }

        viewModel.itemQuantity.observe(this) {
            binding.countNumber.setText(viewModel.itemQuantity.value.toString())
        }

        viewModel.itemPrice.observe(this) {
            binding.itemPrice.setText("Rs." + viewModel.itemPrice.value.toString())
        }

    }

    private fun displaySnackbar(count: Int) {
        Snackbar.make(binding.root, "Total Price: $count", Snackbar.LENGTH_LONG).show()
    }
}