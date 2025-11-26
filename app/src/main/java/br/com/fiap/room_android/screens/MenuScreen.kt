package br.com.fiap.room_android.screens

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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.room_android.R

@Composable
fun MenuScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding(),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            // ---- HEADER ----
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .background(color = Color.White),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween

                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_vinheria),
                            contentDescription = "Logo da loja Vinheria Agnello",
                            modifier = Modifier
                                .size(60.dp)
                        )
                        Text(
                            text = "Vinheria ",
                            color = Color(0xFF151D45),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Agnello",
                            color = Color(0xFF8C2A38),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row(modifier = Modifier.size(60.dp), horizontalArrangement = Arrangement.Center){
                        Image(
                            painter = painterResource(id = R.drawable.usuario),
                            contentDescription = "Foto do usuário",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(40.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.2f),
                                    Color.Transparent
                                )
                            )
                        )
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,

            ){
            Button(
                onClick = { navController.navigate("vinhos") },
                colors = ButtonDefaults.buttonColors(Color(0xFF8C2A38)),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(80.dp)
            ) {
                Text(
                    text = "Adicionar vinho",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = {  navController.navigate("lista") },
                colors = ButtonDefaults.buttonColors(Color(0xFF8C2A38)),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(80.dp)
            ) {
                Text(
                    text = "Lista de vinhos",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}
