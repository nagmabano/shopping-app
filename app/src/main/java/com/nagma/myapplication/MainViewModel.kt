package com.nagma.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nagma.myapplication.data.Product
import com.nagma.myapplication.data.ProductRepository

class MainViewModel(app:Application): AndroidViewModel(app) {

    private val _itemQuantity: MutableLiveData<Int> = MutableLiveData()
    val itemQuantity:LiveData<Int> = _itemQuantity
    private val _itemPrice: MutableLiveData<Int> = MutableLiveData()
    val itemPrice:LiveData<Int> = _itemPrice

//    val products: MutableLiveData<List<Product>> = MutableLiveData()


    var productRepository: ProductRepository = ProductRepository()

    val products: LiveData<List<Product>> = liveData {
        val data = productRepository.getProducts()
        emit(data)
    }

//    init {
//        val data = productRepository.getProducts(app, "product.json")
//        data?.let {
//            products.value = it
//        }
//    }

    init {
        _itemQuantity.value = 0
        _itemPrice.value = 50
    }

    fun increaseItemCount() {
        _itemQuantity.value = _itemQuantity.value!! + 1
    }

    fun decreaseItemCount() {
        if (_itemQuantity.value!! > 0) {
            _itemQuantity.value = _itemQuantity.value!! - 1
        }
    }

    fun getPrice(): Int {
        return itemQuantity.value!! * itemPrice.value!!
    }
}