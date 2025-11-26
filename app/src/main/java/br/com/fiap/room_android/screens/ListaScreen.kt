package br.com.fiap.room_android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.room_android.model.Vinho
import br.com.fiap.room_android.repository.VinhoRepository

@Composable
fun ListaScreen(navController: NavController) {

    val context = LocalContext.current
    val vinhoRepository = VinhoRepository(context)

    val listaVinhosState = remember {
        mutableStateOf(vinhoRepository.listar())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            VinhoList(
                listaVinhosState,
                navController = navController,
                atualizar = {
                    listaVinhosState.value = vinhoRepository.listar()
                },

                )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("menu")
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(80.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F0F0)),
        ) {
            Text(
                text = "Voltar",
                color = Color(0xFF8C2A38),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun VinhoList(
    listaVinhos: MutableState<List<Vinho>>,
    atualizar: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        if (listaVinhos.value.isEmpty()) {
            Text(text = "Nenhum vinho cadastrado.", modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            for (vinho in listaVinhos.value) {
                VinhoCard(vinho, atualizar, navController = navController)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

// Para usar o onClick no Card, precisamos desta anotação.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VinhoCard(
    vinho: Vinho,
    atualizar: () -> Unit,
    navController: NavController
) {
    // Ação de clique adicionada diretamente ao Card.
    Card(
        onClick = { navController.navigate("atualizacao_vinho/${vinho.id}") },
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4F4F4)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        val context = LocalContext.current

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = vinho.nome,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8C2A38)
                )
                Text(
                    text = "Marca: ${vinho.marca}",
                    fontSize = 16.sp,
                )
                Text(
                    text = "Origem: ${vinho.paisOrigem}",
                    fontSize = 16.sp,
                )
                Text(
                    text = "Envelhecimento: ${vinho.tempoEnvelhecimento}",
                    fontSize = 16.sp,
                )
                Text(
                    text = "Tipo: ${vinho.tipo}",
                    fontSize = 16.sp,
                )
                Text(
                    text = "Ocasião: ${vinho.ocasiao}",
                    fontSize = 16.sp,
                )
            }

            Row(){
                IconButton(onClick = {
                    navController.navigate("atualizacao_vinho/${vinho.id}")
                }) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Atualizar Vinho",
                        tint = Color(0xFF8C2A38)
                    )
                }


                IconButton(onClick = {
                    val vinhoRepository = VinhoRepository(context)
                    vinhoRepository.deletar(vinho)
                    atualizar()
                }) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Deletar Vinho",
                        tint = Color(0xFF8C2A38)
                    )
                }
            }



        }
    }
}