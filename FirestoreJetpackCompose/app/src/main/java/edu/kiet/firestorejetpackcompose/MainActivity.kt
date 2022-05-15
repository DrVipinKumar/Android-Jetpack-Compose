package edu.kiet.firestorejetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
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
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.kiet.firestorejetpackcompose.ui.theme.FirestoreJetpackComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirestoreJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FireStoreDB()
                }
            }
        }
    }
}

@Composable
fun FireStoreDB() {
    val db = Firebase.firestore
    val context= LocalContext.current
    var rollno by remember { mutableStateOf("")}
    var sname by remember { mutableStateOf("")}
    var course by remember { mutableStateOf("")}
    var check by remember { mutableStateOf<Boolean>(false)}
    var report by remember { mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Student Info from Firestore",
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
                         val students = hashMapOf(
                             "rollno" to rollno,
                             "stdentname" to sname,
                             "course" to course
                         )

// Add a new document with a generated ID
                         db.collection("students")
                             .add(students)
                             .addOnSuccessListener { documentReference ->
                                 Toast.makeText(context,"Record Inserted with ID: ${documentReference.id}",Toast.LENGTH_SHORT).show()
                             }
                             .addOnFailureListener { e ->
                                 Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
                             }
                     } else
                     {
                         Toast.makeText(context,"Please insert record first",Toast.LENGTH_SHORT).show()
                     }

            }) {
                Text(text = "Insert", fontSize = 16.sp, color = Color.Red)
            }
            OutlinedButton(onClick = {
                var data =StringBuffer()
                db.collection("students")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            data.append("Record ID="+document.id+"\n")
                            data.append("Roll No="+document.get("rollno")+"\n")
                            data.append("Student Name="+document.get("stdentname")+"\n")
                            data.append("Student Course="+document.get("course")+"\n")
                            data.append("======================================\n")

                        }
                        check=true
                        report=data.toString()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(context,exception.toString(),Toast.LENGTH_SHORT).show()
                    }
            }) {
                Text(text = "Display", fontSize = 16.sp, color = Color.Red)
            }
            OutlinedButton(onClick = {
                if (rollno.isNotEmpty() && sname.isNotEmpty() && course.isNotEmpty()){
                    val students = hashMapOf(
                        "rollno" to rollno,
                        "stdentname" to sname,
                        "course" to course
                    )

// Add a new document with a generated ID
                   val query= db.collection("students")
                        .whereEqualTo("rollo",rollno.toInt())
                        .get()
                    query.addOnSuccessListener { it ->
                            for (document in it){
                                db.collection("students").document(document.id).set(students,
                                    SetOptions.merge())
                            }
                            Toast.makeText(context,"Record Updated!",Toast.LENGTH_SHORT).show()

                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
                        }
                } else
                {
                    Toast.makeText(context,"Please insert record first",Toast.LENGTH_SHORT).show()
                }

            }) {
                Text(text = "Update", fontSize = 16.sp, color = Color.Red)
            }
            OutlinedButton(onClick = {

            }) {
                Text(text = "Delete", fontSize = 16.sp, color = Color.Red)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(visible = check, Modifier.fillMaxWidth()) {
            Text(text = report, fontSize = 14.sp, color = Color.Red)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirestoreJetpackComposeTheme {
        FireStoreDB()
    }
}