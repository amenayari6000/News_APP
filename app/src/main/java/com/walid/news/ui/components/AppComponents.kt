package com.walid.news.ui.components








import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

import com.walid.news.R
import com.walid.news.data.entity.Article

import com.walid.news.ui.theme.Purple40
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
 LaunchedEffect(Unit) {
  delay(2000) // Simulate loading time
  navController.navigate("homeScreen") {
   popUpTo("splash") { inclusive = true }
  }
 }

 Box(
  modifier = Modifier
   .fillMaxSize()
   .background(Color.White)
 ) {
  Image(
   painter = painterResource(id = R.drawable.my_app_logo),
   contentDescription = "Splash Logo",
   modifier = Modifier.fillMaxSize(),
   contentScale = ContentScale.Crop // Fills the entire screen
  )
 }
}






@Composable
fun Loader() {

 Column(
  modifier = Modifier
   .fillMaxSize()
   .padding((8.dp)),

  horizontalAlignment = Alignment.CenterHorizontally,
  verticalArrangement = Arrangement.Center


 ) {

  CircularProgressIndicator(
   modifier = Modifier
    .size(60.dp)  // Corrected parentheses
    .padding(10.dp), // Now correctly chained
   color = Purple40 // Moved to the correct position

  )
 }
}


/*@Composable
fun NewsList(response: NewsResponse){
LazyColumn {
items(response.articles) { article ->
 NormalTextComponent(textValue = article.title ?:"NA")

 }


 }


}*/



@Composable
fun NormalTextComponent(textValue: String) {
 Text(
  modifier = Modifier
   .fillMaxWidth()
   .wrapContentHeight()
   .padding(8.dp),
  text = "Debug: $textValue", // Added "Debug:" to ensure text is visible
  style = TextStyle(
   fontSize = 18.sp,
   fontWeight = FontWeight.Bold,
   fontFamily = FontFamily.Monospace
  ),

 )
}







@Composable

fun HeadingTextComponent(textValue: String,centerAligned: Boolean=false) {
 Text(
 modifier = Modifier
  .fillMaxWidth()
  .wrapContentHeight()
  .padding(8.dp),
 text=textValue,
  color = Color.Red,
 style = TextStyle(
  fontSize = 24.sp,
  fontWeight = FontWeight.Thin
 ),
textAlign = if(centerAligned) TextAlign.Center else TextAlign.Start
 )
}

@Composable
fun NewsRowComponent(page: Int, article: Article) {
 LazyColumn(
  modifier = Modifier
   .fillMaxSize()
   .padding(8.dp)
   .background(Color.White)
 ) {
  item {
   Text(text = "Page: $page", color = Color.Black)

   AsyncImage(
    modifier = Modifier
     .fillMaxWidth()
     .height(240.dp)
     .wrapContentHeight()
     .padding(8.dp),
    model = article.urlToImage,
    contentDescription = "",
    contentScale = ContentScale.Crop,
    placeholder = painterResource(id = R.drawable.ic_placeholder_image),
    error = painterResource(id = R.drawable.ic_placeholder_image)
   )

   Spacer(modifier = Modifier.size(20.dp))
   HeadingTextComponent(textValue = article.title ?: "")

   Spacer(modifier = Modifier.size(10.dp))
   NormalTextComponent(textValue = article.description ?: "No Description Available")

  // Spacer(modifier = Modifier.size(10.dp))
  NormalTextComponent(textValue = article.content ?: "No Content Available")

   Spacer(modifier = Modifier.size(10.dp))
  // NormalTextComponent(textValue = article.author ?: "No Author")
 AuthorDetailsComponent(article.author,article.source?.name)
  }
 }
}

/*@Preview(showBackground = true)
@Composable
fun NewsRowComponentPreview() {
 val article = Article(
  source = Source(id = "associated-press", name = "Associated Press"),
  author = "Josh Kirshenbaum",
  title = "Derik Queen's buzzer-beating fadeaway gives Maryland a 72-71 March Madness win over Colorado State",
  description = "Derik Queen banked in a fadeaway jumper at the buzzer, and Maryland advanced to the Sweet 16 in a thriller, beating Colorado State 72-71 in the second round of the NCAA Tournament.",
  url = "https://apnews.com/article/march-madness-colorado-state-maryland-score-003b99c846e93e5d41bca08b6375f7bc",
  urlToImage = "https://dims.apnews.com/dims4/default/fa19e41/2147483647/strip/true/crop/5494x3090+0+286/resize/1440x810!/quality/90/?url=https%3A%2F%2Fassets.apnews.com%2Fb5%2F7c%2F14cf5d7ef19a658e253306f358f6%2F47ea26a51ca5469d8b820291c25d58f3",
  publishedAt = "2025-03-24T12:02:00Z",
  content = "SEATTLE (AP) Derik Queen demanded the ball..."
 )

 NewsRowComponent(page=0,article=article)
}*/

@Composable
fun AuthorDetailsComponent (authorName: String?,sourceName: String?){
 Row (modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end =10.dp, bottom =20.dp)){
  authorName?.also{
  Text(text =it )

  }

  Spacer(modifier = Modifier.weight(1f))
  sourceName?.also {
 Text(text =it)

  }

 }

}

@Composable
fun EmptyStateComponent(){
 Column(
  modifier = Modifier.fillMaxSize()
.padding(16.dp),
  horizontalAlignment = Alignment.CenterHorizontally,
  verticalArrangement = Arrangement.Center

 ) {
Image(
  painter=painterResource(id=R.drawable.ic_no_data),
   contentDescription ="No news as of now\nPlease check in some time!",
 modifier = Modifier.fillMaxWidth().height(300.dp)


)
  HeadingTextComponent(textValue="No news as of now\nPlease check in some time!",
  centerAligned= true)
 }
}

@Preview
@Composable
fun EmptyStateComponentPreview(){
 EmptyStateComponent()
}
