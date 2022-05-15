package edu.kiet.loginjetpackcompose


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.kiet.loginjetpackcompose.ui.theme.LoginJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Login()
                }
            }
        }
    }
    @Composable
    fun Login() {
        val username=remember { mutableStateOf("")}
        val password=remember { mutableStateOf("")}
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login Information", fontSize = 30.sp,
                color = Color.Red, modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
                    , textAlign = TextAlign.Center )
            OutlinedTextField(value =username.value , onValueChange = {
                username.value=it
            },modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colors.primary)
                ,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, tint=Color.Red,contentDescription ="username" )
                },
                placeholder ={
                    Text("Username",color=Color.Red)
                }
            )
            OutlinedTextField(value =password.value , onValueChange = {
                password.value=it
            },  modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colors.primary)
               ,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Info, tint=Color.Red,contentDescription ="password" )
                },
               placeholder={
                    Text("Password",color=Color.Red)
                }
            )
            OutlinedButton(onClick = {
                if (username.value.equals("vipin") && password.value.equals("classes")){
                    val intent= Intent(applicationContext,Welcome::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext,"Username or Password in Incorrect",Toast.LENGTH_SHORT).show()
                }
            },
                modifier = Modifier.fillMaxWidth()) {
                Text("Login", fontSize = 30.sp,color=Color.Red)

            }

        }

    }

    @Preview(showBackground = true,name="DarkMode",
        uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Preview(showBackground = true,name="NormalMode")
    @Composable
    fun DefaultPreview() {
        LoginJetpackComposeTheme {
            Login()
        }

    }

}