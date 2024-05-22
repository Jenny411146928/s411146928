package tw.edu.pu.csim.s411146928

import android.R
import android.R.id
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tw.edu.pu.csim.s411146928.ui.theme.S411146928Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S411146928Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    Main()
                    //FirstScreen()
                    //SecondScreen()
                }
            }
        }
    }


    @Composable
    fun FirstScreen(navController: NavController) {
        val context = LocalContext.current
        var showTitle by remember { mutableStateOf(true) }
        var expanded by remember { mutableStateOf(true) }
        var currentImage by remember { mutableStateOf(tw.edu.pu.csim.s411146928.R.drawable.service)}

        Column(modifier = Modifier)
        {
            Text(text = if (currentImage == tw.edu.pu.csim.s411146928.R.drawable.service)"瑪利亞基金會服務總覽" else "關於App作者", style = TextStyle(color = Color.Blue))

            var alpha by remember {
                mutableStateOf(1f)
            }
            LaunchedEffect(currentImage){
                val animatable = androidx.compose.animation.core.Animatable(0f)
                animatable.animateTo(1f, animationSpec = tween(5000)){
                    alpha = value
                }
            }

            Image(painter = painterResource(currentImage), contentDescription = "service", modifier = Modifier
                .clip(RectangleShape)
                .fillMaxWidth()
                .height(if(expanded)600.dp else 400.dp)
                .clickable { expanded = !expanded }
                .alpha(alpha)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                showTitle = !showTitle
                currentImage = if (currentImage == tw.edu.pu.csim.s411146928.R.drawable.service) {
                    tw.edu.pu.csim.s411146928.R.drawable.j
                } else {
                    tw.edu.pu.csim.s411146928.R.drawable.service
                }
            }) {
                Text(text = if (currentImage == tw.edu.pu.csim.s411146928.R.drawable.service) "作者：資管系李盈蓁" else "服務總覽")
            }
        }
    }


    @Composable
    fun SecondScreen(navController: NavController) {
        val context = LocalContext.current

        Column(modifier = Modifier)

        {
            Text("主要機構", style = TextStyle(color = Color.Red)) //顏色改紅色
        }

    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Main() {
        val navController = rememberNavController()
        val context = LocalContext.current
        var showMenu by remember {
            mutableStateOf(false)
        }

        Column {
            TopAppBar(
                title = { Text(text = "")
                    Image(
                        painter = painterResource(id = tw.edu.pu.csim.s411146928.R.drawable.maria), contentDescription = null, modifier = Modifier
                            .height(50.dp),
                    )

                },
                actions = {
                    IconButton(
                        onClick = { showMenu = true }
                    ) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(
                        expanded = showMenu, onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("簡介") },
                            onClick = { navController.navigate("JumpFirst") }
                        )

                        DropdownMenuItem(
                            text = { Text("主要機構") },
                            onClick = { navController.navigate("JumpSecond") }
                        )

                    }
                }
            )

            NavHost(navController = navController, startDestination = "JumpFirst") {
                composable("JumpFirst") {
                    FirstScreen(navController = navController)
                }
                composable("JumpSecond") {
                    SecondScreen(navController = navController)
                }
            }
        }
    }
}