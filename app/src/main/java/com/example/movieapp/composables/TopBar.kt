package com.example.movieapp.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.ui.theme.Typography

@Composable
fun TopBar(
    name: String,
    navController: NavController? = null,
    imageVector: ImageVector? = null
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            imageVector?.let {
                navController?.let { navController ->
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "Arrow back",
                        modifier = Modifier
                            .clickable { navController.popBackStack() }
                            .size(34.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = name,
                style = Typography.h4
            )
        }
    }
}