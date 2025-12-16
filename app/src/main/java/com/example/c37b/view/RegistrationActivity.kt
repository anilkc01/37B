package com.example.c37b.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c37b.R
import com.example.c37b.model.UserModel
import com.example.c37b.repository.UserRepoImpl
import com.example.c37b.ui.theme.Blue
import com.example.c37b.ui.theme.Purple80
import com.example.c37b.ui.theme.White
import com.example.c37b.viewModel.UserViewModel
import java.util.Calendar

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterBody()
        }
    }
}

@Composable
fun RegisterBody() {

    val userViewModel = UserViewModel(UserRepoImpl())

    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }

    var checkbox by remember { mutableStateOf(false) }


    val context = LocalContext.current
    val activity = context as Activity

    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var selectedDate by remember { mutableStateOf("") }

    val sharedPreference = context.getSharedPreferences(
        "User",
        Context.MODE_PRIVATE
    )


    val editor = sharedPreference.edit()


    var datepicker = DatePickerDialog(
        context, { _, y, m, d ->
            selectedDate = "$d/${m + 1}/$y"

        }, year, month, day
    )


    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "Sign Up",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    color = Blue,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                "This app is a ecommere app. welcome to app here you can browse products. Lorem epsum hljklnabva",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Color.Gray.copy(0.8f)
                ),
                modifier = Modifier.padding(vertical = 20.dp)
            )


            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = firstName,
                onValueChange = { data ->
                    firstName = data
                },
                placeholder = {
                    Text("First name")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Purple80,
                    focusedContainerColor = Purple80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = { data ->
                    lastName = data
                },
                placeholder = {
                    Text("Last Name")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Purple80,
                    focusedContainerColor = Purple80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = gender,
                onValueChange = { data ->
                    gender = data
                },
                placeholder = {
                    Text("Gender")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Purple80,
                    focusedContainerColor = Purple80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { data ->
                    email = data
                },
                placeholder = {
                    Text("abc@gmail.com")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Purple80,
                    focusedContainerColor = Purple80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = selectedDate,
                onValueChange = { data ->
                    selectedDate = data
                },
                placeholder = {
                    Text("dd/mm/yyyy")
                },
                enabled = false,
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = Purple80,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Purple80,
                    focusedContainerColor = Purple80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        datepicker.show()
                    }
                    .padding(horizontal = 15.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text("*******")
                },

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Purple80,
                    focusedContainerColor = Purple80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                visualTransformation = if (visibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        visibility = !visibility
                    }) {
                        Icon(
                            painter = if (visibility)
                                painterResource(R.drawable.baseline_visibility_off_24)
                            else painterResource(
                                R.drawable.baseline_visibility_24
                            ),


                            contentDescription = null
                        )
                    }
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkbox,
                    onCheckedChange = {
                        checkbox = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = White,
                        checkedColor = Color.Green
                    )
                )
                Text("I agree to terms & Conditions")
            }

            Button(onClick = {
                if (!checkbox) {
                    Toast.makeText(
                        context,
                        "Please accept terms & conditions",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    userViewModel.register(email, password){success,message,userId->
                        if(success){
                            val model = UserModel(
                                id = userId,
                                firstName = firstName,
                                lastName = lastName,
                                gender = gender,
                                dob = selectedDate,
                                email = email
                            )
                            userViewModel.addUserToDatabase(userId,model){success,message->
                                if(success){
                                    Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                                    activity.finish()

                                }else{
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                }
                            }


                        }else{
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }
            },
                modifier = Modifier.fillMaxWidth()
                ) {
                Text("Register")
            }


        }
    }

}

@Preview
@Composable
fun RegisterPreview() {
    RegisterBody()
}