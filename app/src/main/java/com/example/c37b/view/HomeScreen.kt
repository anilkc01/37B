package com.example.c37b.view


import android.R.attr.data
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon

import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.c37b.R
import com.example.c37b.model.ProductModel
import com.example.c37b.repository.ProductRepoImpl
import com.example.c37b.viewModel.ProductViewModel

@Composable
fun HomeScreen(){

    val productViewModel = remember { ProductViewModel(ProductRepoImpl()) }

    val context = LocalContext.current

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    val products  = productViewModel.allProducts.observeAsState(initial = emptyList())

    val data = productViewModel.products.observeAsState(initial = null)

    var pName by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }

    LaunchedEffect(data.value) {
        productViewModel.getAllProduct()

        data.value?.let { product->
            pName = product.productName
            pPrice = product.price.toString()
            pDesc = product.description
        }
    }



    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color.White)
    ) {
        item {
            if (showEditDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showEditDialog = false
                    }, // dismiss when clicked outside
                    confirmButton = {
                        TextButton (onClick = {
                            val model = ProductModel(
                                productId = data.value!!.productId,
                                productName = pName,
                                price = pPrice.toDouble(),
                                description = pDesc,
                                categoryId = data.value!!.categoryId

                            )
                            productViewModel.updateProduct(model){
                                    success,message->
                                if(success){
                                    showEditDialog = false
                                }else{
                                    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }) {
                            Text("Update")
                        }
                    },
                    dismissButton = {
                        TextButton (onClick = {
                            // Cancel action
                            showEditDialog = false
                        }) {
                            Text("Cancel")
                        }
                    },
                    title = { Text(text = "Update Product") },
                    text = { Column {
                        Text("Product name")
                        OutlinedTextField(
                            value = pName,
                            onValueChange = {pName = it},
                            placeholder = {Text(data.value!!.productName)}

                        )
                        Text("Product price")
                        OutlinedTextField(
                            value = pPrice,
                            onValueChange = {pPrice = it},
                            placeholder = {Text(pPrice)}
                        )
                        Text("Product description")
                        OutlinedTextField(
                            value = pDesc,
                            onValueChange = {pDesc = it},
                            placeholder = {Text(pDesc)}

                            )
                    } }
                )
            }

            if (showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDeleteDialog = false
                    }, // dismiss when clicked outside
                    confirmButton = {
                        Button(onClick = {
                            productViewModel.deleteProduct(data.value!!.productId){success,message->
                                if(success){
                                    showDeleteDialog = false
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                }
                            }

                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            // Cancel action
                            showDeleteDialog = false
                        }) {
                            Text("Cancel")
                        }
                    },
                    title = { Text(text = "Delete Product") },
                    text = { Text("Are you sure. to delete this product?.") }
                )
            }
        }

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
                        showEditDialog = true
                        productViewModel.getProductById(data.productId)
                    }){
                        Icon(
                            painter = painterResource(R.drawable.baseline_edit_24),
                            contentDescription = null
                        )
                    }

                    IconButton(onClick ={
                        showDeleteDialog = true
                        productViewModel.getProductById(data.productId)

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
