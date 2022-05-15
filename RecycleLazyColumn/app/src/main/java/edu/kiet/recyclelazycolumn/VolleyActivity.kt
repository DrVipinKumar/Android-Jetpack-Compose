package edu.kiet.recyclelazycolumn

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import edu.kiet.recyclelazycolumn.retrofit.UserData
import edu.kiet.recyclelazycolumn.retrofit.UserDataItem
import edu.kiet.recyclelazycolumn.ui.theme.RecycleLazyColumnTheme

class VolleyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecycleLazyColumnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
    @Composable
    fun OneRow(data: UserDataItem){

        Card(modifier= Modifier
            .fillMaxWidth()
            .padding(5.dp),
            shape = RoundedCornerShape(CornerSize(10.dp)),
            elevation = 2.dp

        ) {
            Row(modifier=Modifier.fillMaxWidth()) {
                Image(painter = rememberImagePainter(data.avatar_url), contentDescription ="image",
                    modifier= Modifier
                        .padding(5.dp)
                        .size(150.dp)
                        .clip(RoundedCornerShape(CornerSize(10.dp)))

                )
                Text(text=data.login, fontWeight = FontWeight.Bold, fontSize = 30.sp,textAlign= TextAlign.Center,
                    modifier= Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .clickable {
                            Toast
                                .makeText(
                                    applicationContext,
                                    "You clicked on " + data.login,
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                )

            }
        }

    }
    @Composable
    fun Greeting() {
        val userData =UserData()
        val baseUrl:String="https://api.github.com/users"
        val data= remember { mutableStateOf<UserData>(UserData()) }
        val stringRequest =StringRequest(baseUrl, {
            val gsonBuilder = GsonBuilder()
            val gson = gsonBuilder.create()
            gson.fromJson(it, Array<UserDataItem>::class.java)?.forEach {
                userData.add(it)
            }
            data.value = userData
        }, {
            Toast.makeText(applicationContext,it.toString(),Toast.LENGTH_SHORT).show()
        }).apply { LazyColumn{
            items(data.value){
                    data-> OneRow(data = data)
            }
        } }
        val volleyRequest = Volley.newRequestQueue(applicationContext)
        volleyRequest.add(stringRequest)
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        RecycleLazyColumnTheme {
            Greeting()
        }
    }
}

