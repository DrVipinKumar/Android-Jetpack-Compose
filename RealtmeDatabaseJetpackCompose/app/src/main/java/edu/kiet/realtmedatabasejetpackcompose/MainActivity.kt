package edu.kiet.realtmedatabasejetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.kiet.realtmedatabasejetpackcompose.ui.theme.RealtmeDatabaseJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealtmeDatabaseJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RealTimeDatabse()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RealTimeDatabse() {
    val context= LocalContext.current
    val database = Firebase.database
    val myRef = database.getReference("Students")
    var rollno by remember { mutableStateOf("")}
    var sname by remember { mutableStateOf("")}
    var course by remember { mutableStateOf("")}
    var check by remember { mutableStateOf<Boolean>(false)}
    var result by remember { mutableStateOf("")}
     Column(
         modifier = Modifier.fillMaxWidth(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
     ) {
         Text(text = "Student Info from Realtime Database",
             fontSize = 20.sp,
             color = Color.Red
         )
         Spacer(modifier = Modifier.height(10.dp))
         OutlinedTextField(value = rollno, onValueChange ={rollno=it},
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(10.dp),
             placeholder = {
                 Text(text = "Enter student rollno", fontSize = 14.sp, color = Color.Blue)
             }
         )
         Spacer(modifier = Modifier.height(10.dp))
         OutlinedTextField(value = sname, onValueChange ={sname=it},
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(10.dp),
             placeholder = {
                 Text(text = "Enter student name", fontSize = 14.sp, color = Color.Blue)
             }
         )
         Spacer(modifier = Modifier.height(10.dp))
         OutlinedTextField(value = course, onValueChange ={course=it},
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(10.dp),
             placeholder = {
                 Text(text = "Enter student course", fontSize = 14.sp, color = Color.Blue)
             }
         )
         Spacer(modifier = Modifier.height(20.dp))
         Row() {
             OutlinedButton(onClick = {
                 if (rollno.isNotEmpty() && sname.isNotEmpty() && course.isNotEmpty()){
                     val sinfo =StudentInfo(rollno.toInt(),sname,course)
                     myRef.child(sname).setValue(sinfo).addOnSuccessListener {
                         rollno=""
                         sname=""
                         course=""
                         Toast.makeText(context,"Record Inserted",Toast.LENGTH_SHORT).show()

                     }.addOnFailureListener{
                         Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
                     }
                 } else{
                     Toast.makeText(context,"Please Insert value first",Toast.LENGTH_SHORT).show()
                 }
             }) {
                 Text(text = "Insert", fontSize = 16.sp, color = Color.Red)
             }
             OutlinedButton(onClick = {
                 val data=StringBuffer()
                myRef.get().addOnSuccessListener {
                     if (it.exists())
                     {
                         it.children.forEach { it ->
                             data.append("\nStudent Roll No="+it.child("rollno").value)
                             data.append("\nStudent Name="+it.child("sname").value)
                             data.append("\nStudent Course="+it.child("course").value)
                             data.append("\n")
                         }
                         check=true
                         result=data.toString()
                     }
                 }.addOnFailureListener{
                     Toast.makeText(context,"Record not found",Toast.LENGTH_SHORT).show()
                 }
             }) {
                 Text(text = "Display", fontSize = 16.sp, color = Color.Red)
             }
             OutlinedButton(onClick = {
                 if (rollno.isNotEmpty() && sname.isNotEmpty() && course.isNotEmpty()){
                     val sinfo = mapOf<String, String>(
                         "rollno" to rollno,
                         "sname" to sname,
                         "course" to course
                     )
                     myRef.child(sname).updateChildren(sinfo).addOnSuccessListener {
                         rollno=""
                         sname=""
                         course=""
                         Toast.makeText(context,"Record Updated",Toast.LENGTH_SHORT).show()

                     }.addOnFailureListener{
                         Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
                     }
                 } else{
                     Toast.makeText(context,"Please Insert value first",Toast.LENGTH_SHORT).show()
                 }
             }) {
                 Text(text = "Update", fontSize = 16.sp, color = Color.Red)
             }
             OutlinedButton(onClick = {
                 if (sname.isNotEmpty()){
                     myRef.child(sname).removeValue().addOnSuccessListener {
                         Toast.makeText(context,"Record Deleted",Toast.LENGTH_SHORT).show()
                     }.addOnFailureListener{
                         Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
                     }

                 } else
                 {
                     Toast.makeText(context,"Please Enter Name to Delete Record",Toast.LENGTH_SHORT).show()
                 }
             }) {
                 Text(text = "Delete", fontSize = 16.sp, color = Color.Red)
             }
         }
         Spacer(modifier = Modifier.height(10.dp))
         AnimatedVisibility(visible = check, Modifier.fillMaxWidth()) {
             Text(text = result, fontSize = 14.sp, color = Color.Red)
         }

     }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RealtmeDatabaseJetpackComposeTheme {
        RealTimeDatabse()
    }
}