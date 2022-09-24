package com.mungaicodes.marsphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.mungaicodes.marsphotos.model.MarsPhoto
import com.mungaicodes.marsphotos.ui.theme.AppViewModel
import com.mungaicodes.marsphotos.ui.theme.MarsPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarsPhotosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MarsPhotoApp()
                }
            }
        }
    }
}

@Composable
fun MarsPhotoApp() {
    val viewModel: AppViewModel = viewModel()

    MarsPhotosList(photos = viewModel.marsPhotos)
}

@Composable
fun MarsPhotosList(
    photos: List<MarsPhoto>
) {


    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        items(photos) { photo ->
            PhotoCard(photo = photo)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoCard(photo: MarsPhoto) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(4.dp, Color.DarkGray),
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .wrapContentSize()
            .background(Color.LightGray),
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberImagePainter(
                    data = photo.img_src,
                    builder = {
                        scale(Scale.FILL)
                        placeholder(R.drawable.loading_animation)
                    }
                ),
                contentDescription = photo.img_src,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarsPhotosTheme {
        PhotoCard(photo = MarsPhoto("", ""))
    }
}