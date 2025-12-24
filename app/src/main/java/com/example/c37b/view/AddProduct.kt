package com.example.c37b.view

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.c37b.R
import com.example.c37b.model.ProductModel
import com.example.c37b.repository.ProductRepoImpl
import com.example.c37b.repository.commonRepoImpl
import com.example.c37b.viewModel.CommonViewModel
import com.example.c37b.viewModel.ProductViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AddProduct() {
    val context = LocalContext.current

    var productName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var cloudinaryLink by remember {mutableStateOf("")}

    var productViewModel = ProductViewModel(ProductRepoImpl())
    var commonViewModel = CommonViewModel(commonRepoImpl())


    val imagePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            imageUri = uri
            imageUri?.let {
                commonViewModel.uploadImage(context,it){success,imageUrl->
                    if(success){
                        cloudinaryLink=imageUrl!!.toString()
                    }else{
                        Toast.makeText(context, "Image Not Uploaded", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Add Product",
                style = MaterialTheme.typography.headlineSmall
            )

            // Image Picker
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri == null) {
                    Image(
                        painter = painterResource(R.drawable.placeholder),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Product Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = categoryId,
                onValueChange = { categoryId = it },
                label = { Text("Category ID") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val product = ProductModel(
                        productName = productName,
                        description = description,
                        price = price.toDoubleOrNull() ?: 0.0,
                        quantity = quantity.toIntOrNull() ?: 0,
                        categoryId = categoryId,
                        productImage =  cloudinaryLink
                    )

                    productViewModel.addProduct(product){success,message->
                        if(success){
                            Toast.makeText(context, "Product Added", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Product")
            }
        }
    }
}