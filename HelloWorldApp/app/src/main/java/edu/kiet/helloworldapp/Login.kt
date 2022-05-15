package edu.kiet.helloworldapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.kiet.helloworldapp.ui.theme.HelloWorldAppTheme
import kotlinx.coroutines.currentCoroutineContext

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginInfo()
                }
            }
        }
    }

    @Composable
    private fun LoginInfo() {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login Information", fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = username.value,
                onValueChange = {
                    username.value = it
                }, leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        tint = Color.Black,
                        contentDescription = "login"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Red,
                    unfocusedLabelColor = Color.Blue,
                    focusedLabelColor = Color.Blue,
                    placeholderColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Username")
                },
                placeholder = {
                    Text(text = "Enter username")
                }
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        tint = Color.Black,
                        contentDescription = "password"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Red,
                    unfocusedLabelColor = Color.Blue,
                    focusedLabelColor = Color.Blue
                ),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Password")
                },
                placeholder = {
                    Text(text = "Enter password")
                }

            )
            OutlinedButton(onClick = {
                if (username.value.equals("vipin") && password.value.equals("kumar")) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Username or Password Incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Login", fontSize = 30.sp)
            }
        }

    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    HelloWorldAppTheme {
        LoginInfo()
    }
}
}