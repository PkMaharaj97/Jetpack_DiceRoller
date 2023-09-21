package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.diceroller.model.ImageContent
import com.example.diceroller.model.loadImagesContents
import com.example.diceroller.ui.theme.DiceRollerTheme

class ArtSpaceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImagesListApp()
                }
            }
        }
    }
}




@Composable
fun ImageContentApp(){
    ImageContentList(loadImagesContents())
}

@Composable
fun ImageContentList(imageList:List<ImageContent>,modifier: Modifier=Modifier){
    LazyColumn(modifier=modifier){
        items(imageList){imageItem->
            ImageCard(
                imageContent = imageItem,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

@Composable
fun ImageCard(modifier: Modifier=Modifier,imageContent: ImageContent){
    Card(modifier = modifier, colors = CardDefaults.cardColors(
        containerColor = Color.Green,
    )) {
        Column {
            AsyncImage(model = imageContent.ImageUrl,
                contentDescription = imageContent.Content,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop)

            Text(
                modifier=Modifier.padding(10.dp),
                text = imageContent.Content,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontSize= 20.sp,
               style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImagesListApp() {
    DiceRollerTheme {
        ImageContentApp()
    }
}