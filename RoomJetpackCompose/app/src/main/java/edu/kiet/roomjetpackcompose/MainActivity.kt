package edu.kiet.roomjetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.kiet.roomjetpackcompose.roomdata.Student
import edu.kiet.roomjetpackcompose.roomdata.StudentDB
import edu.kiet.roomjetpackcompose.ui.theme.RoomJetpackComposeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RoomDB()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RoomDB() {
    val coroutineScope= rememberCoroutineScope()
    val context= LocalContext.current
    var rollno by remember {mutableStateOf("")}
    var sname by remember {mutableStateOf("")}
    var course by remember {mutableStateOf("")}
    var check by remember {mutableStateOf<Boolean>(false)}
    var result by remember {mutableStateOf("")}
    Column(Modifier.fillMaxWidth(),
       horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Enter Students Information",
        modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Spacer(Modifier.height(30.dp))
        OutlinedTextField(value = rollno, onValueChange ={rollno=it},
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        placeholder = { Text(text = "Enter rollno")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "rollno")
            }
            )
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(value = sname, onValueChange ={sname=it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            placeholder = { Text(text = "Enter student name")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "sname")
            }
        )
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(value = course, onValueChange ={course=it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            placeholder = { Text(text = "Enter course")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "course")
            }
        )
        Spacer(Modifier.height(20.dp))
        Row(Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(onClick = {

                   if(rollno.isNotEmpty() && sname.isNotEmpty() && course.isNotEmpty()){
                      coroutineScope.launch {
                          val checkstudent = StudentDB.getInstance(context).getStudentDao().check(rollno.toInt())
                           if(checkstudent.isEmpty()) {
                               val student = Student(rollno.toInt(), sname, course)
                               StudentDB.getInstance(context).getStudentDao().insert(student)
                               Toast.makeText(context, "Record Inserted", Toast.LENGTH_SHORT).show()
                           } else {
                               Toast.makeText(context, "Change Roll No, Student Already Exist with Same Roll No", Toast.LENGTH_SHORT).show()
                           }
                          }
                   } else {
                       Toast.makeText(context,"Please insert information first!",Toast.LENGTH_SHORT).show()
                   }
            }) {
                Text(text = "Insert", fontSize = 18.sp)
            }
            OutlinedButton(onClick = {

                     coroutineScope.launch {
                         val data=StringBuffer()
                         var i:Int=0
                         val studentInfo=StudentDB.getInstance(context = context).getStudentDao().display()
                         for ( student in studentInfo){
                             i++
                             data.append("Record "+i+"\n")
                             data.append("Roll no="+student.rollno+"\n")
                             data.append("Student Name="+student.studentName+"\n")
                             data.append("Course="+student.course+"\n")
                             data.append("\n")
                         }
                         if (data.isNotEmpty()){
                             check=true
                             result=data.toString()
                         } else
                         {
                             result=""
                             Toast.makeText(context,"Record are not found",Toast.LENGTH_SHORT).show()
                         }
                     }

            }) {
                Text(text = "Display", fontSize = 18.sp)
            }
            OutlinedButton(onClick = {
                if(rollno.isNotEmpty() && sname.isNotEmpty() && course.isNotEmpty()){
                    coroutineScope.launch {
                        val checkstudent=StudentDB.getInstance(context).getStudentDao().check(rollno.toInt())
                        if (checkstudent.isNotEmpty()){
                            val student= Student(rollno.toInt(),sname,course)
                            StudentDB.getInstance(context).getStudentDao().update(student).apply {
                                Toast.makeText(context,"Record Updated",Toast.LENGTH_SHORT).show() }

                        } else{
                            Toast.makeText(context,"Student not Found",Toast.LENGTH_SHORT).show() }
                        }

                } else {
                    Toast.makeText(context,"Please insert information first!",Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Update", fontSize = 18.sp)
            }
            OutlinedButton(onClick = {
                if (rollno.isNotEmpty()){
                    coroutineScope.launch {
                        StudentDB.getInstance(context).getStudentDao().delete(rollno.toInt())
                        Toast.makeText(context,"Record Deleted",Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(context,"Please insert roll no to delete",Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Delete", fontSize = 18.sp)
            }
        }
        Spacer(Modifier.height(20.dp))
        AnimatedVisibility(visible = check,Modifier.fillMaxWidth()) {
            Text(text = result)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomJetpackComposeTheme {
        RoomDB()
    }
}