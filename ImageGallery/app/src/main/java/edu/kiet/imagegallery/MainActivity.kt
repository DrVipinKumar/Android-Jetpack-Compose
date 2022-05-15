package edu.kiet.imagegallery

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.kiet.imagegallery.ui.theme.ImageGalleryTheme



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoadImage()
                }
            }
        }

    }

}

@Composable
fun LoadImage() {
    val context= LocalContext.current
    val myImage:Bitmap=BitmapFactory.decodeResource(Resources.getSystem(),android.R.mipmap.sym_def_app_icon)
    val result=remember { mutableStateOf<Bitmap>(myImage)}
    val loadImage = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        result.value=it
//        if (Build.VERSION.SDK_INT<29)
//        {
//          result.value=MediaStore.Images.Media.getBitmap(context.contentResolver,it)
//        }else
//        {
//            val source =ImageDecoder.createSource(context.contentResolver,it)
//            result.value=ImageDecoder.decodeBitmap(source)
//        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(result.value.asImageBitmap(), contentDescription = "image",
        modifier= Modifier
            .size(300.dp)
            .padding(10.dp))
        OutlinedButton(onClick = { loadImage.launch() },
        modifier=Modifier.fillMaxWidth()
        ){
            Text(text = "Click Photo", fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color= Color.White,
                textAlign=TextAlign.Center,
                modifier= Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageGalleryTheme {
        LoadImage()
    }
}