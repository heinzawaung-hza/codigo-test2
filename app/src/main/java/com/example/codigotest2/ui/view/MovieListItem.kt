package com.example.codigotest2.ui.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.codigotest2.data.model.Movie
import com.example.codigotest2.util.Constants

@Composable
fun MovieListItem(movie: Movie,  onItemClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemClick(movie) },
    )
    {
        Surface(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Movie poster image
                Image(
                    painter = rememberImagePainter(data = Constants.IMAGE_URL + movie.posterPath),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                // Movie details column
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    // Movie title
                    Text(
                        text = movie.title ?: "Unknown Title",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp
                    )

                    // Movie release date
                    Text(
                        text = "Release Date: ${movie.releaseDate ?: "Unknown Date"}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp
                    )

                    // Movie overview
                    Text(
                        text = movie.overview ?: "No overview available",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
