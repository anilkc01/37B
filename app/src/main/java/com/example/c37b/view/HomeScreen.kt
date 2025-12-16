package com.example.c37b.view


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon

import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.c37b.R
import com.example.c37b.repository.ProductRepoImpl
import com.example.c37b.viewModel.ProductViewModel

@Composable
fun HomeScreen(){

    val productViewModel = remember { ProductViewModel(ProductRepoImpl()) }

    LaunchedEffect(Unit) {
        productViewModel.getAllProduct()
    }

    val products  = productViewModel.allProducts.observeAsState(initial = emptyList())

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color.White)
    ) {
        items(products.value!!.size){index ->
            val data = products.value!![index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 15.dp)
            ){
                Column{
                    Text(data.productName)
                    Text(data.description)
                    Text(data.price.toString())
                    IconButton(onClick ={

                    }){
                        Icon(
                            painter = painterResource(R.drawable.baseline_edit_24),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick ={
                        productViewModel.deleteProduct(data.productId){success,message->

                        }

                    }){
                        Icon(
                            painter = painterResource(R.drawable.baseline_delete_24),
                            contentDescription = null
                        )
                    }
                }
                }
            }

        }

}

