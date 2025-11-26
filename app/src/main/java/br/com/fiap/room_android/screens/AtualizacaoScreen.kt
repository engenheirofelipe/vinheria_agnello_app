package br.com.fiap.room_android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.fiap.room_android.R
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.room_android.model.Vinho
import br.com.fiap.room_android.repository.VinhoRepository

@Composable
fun AtualizacaoScreen(navController: NavController, vinhoId: Long) {

    var nomeVinhoState by remember { mutableStateOf("") }
    var marcaState by remember { mutableStateOf("") }
    var paisOrigemState by remember { mutableStateOf("") }
    var tempoEnvelhecimentoState by remember { mutableStateOf("") }
    var tipoState by remember { mutableStateOf("") }
    var ocasiaoState by remember { mutableStateOf("") }

    val context = LocalContext.current
    val vinhoRepository = VinhoRepository(context)

    LaunchedEffect(key1 = vinhoId) {
        val vinho = vinhoRepository.buscarPorId(vinhoId)
        if (vinho != null) {
            nomeVinhoState = vinho.nome
            marcaState = vinho.marca
            paisOrigemState = vinho.paisOrigem
            tempoEnvelhecimentoState = vinho.tempoEnvelhecimento
            tipoState = vinho.tipo
            ocasiaoState = vinho.ocasiao
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // ---- HEADER (sem alterações) ----
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
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Painel de administração",
                    color = Color(0xFF8C2A38),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(32.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFFFF)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {

                    AtualizacaoVinhoForm(
                        id = vinhoId,
                        nome = nomeVinhoState,
                        marca = marcaState,
                        paisOrigem = paisOrigemState,
                        tempoEnvelhecimento = tempoEnvelhecimentoState,
                        tipo = tipoState,
                        ocasiao = ocasiaoState,
                        onNomeChange = { nomeVinhoState = it },
                        onMarcaChange = { marcaState = it },
                        onPaisOrigemChange = { paisOrigemState = it },
                        onTempoEnvelhecimentoChange = { tempoEnvelhecimentoState = it },
                        onOcasiaoChange = { ocasiaoState = it },
                        onTipoChange = { tipoState = it },
                        navController = navController
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtualizacaoVinhoForm(
    id: Long,
    nome: String,
    marca: String,
    paisOrigem: String,
    tempoEnvelhecimento: String,
    tipo: String,
    ocasiao: String,
    onNomeChange: (String) -> Unit,
    onMarcaChange: (String) -> Unit,
    onPaisOrigemChange: (String) -> Unit,
    onTempoEnvelhecimentoChange: (String) -> Unit,
    onTipoChange: (String) -> Unit,
    onOcasiaoChange: (String) -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val vinhoRepository = VinhoRepository(context)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Atualizar Vinho",
            color = Color(0xFF8C2A38),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { onNomeChange(it) },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = marca,
            onValueChange = { onMarcaChange(it) },
            label = { Text("Marca") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = paisOrigem,
            onValueChange = { onPaisOrigemChange(it) },
            label = { Text("País de origem") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = tempoEnvelhecimento,
            onValueChange = { onTempoEnvelhecimentoChange(it) },
            label = { Text("Tempo de envelhecimento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = tipo,
            onValueChange = { onTipoChange(it) },
            label = { Text("Tipo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = ocasiao,
            onValueChange = { onOcasiaoChange(it) },
            label = { Text("Ocasiao") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                onClick = {
                    val vinho = Vinho(
                        id = id,
                        nome = nome,
                        marca = marca,
                        paisOrigem = paisOrigem,
                        tempoEnvelhecimento = tempoEnvelhecimento,
                        tipo = tipo,
                        ocasiao = ocasiao
                    )
                    vinhoRepository.atualizar(vinho)
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8C2A38))
            ) {
                Text(text = "Atualizar", color = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F0F0))
            ) {
                Text(text = "Voltar", color = Color(0xFF8C2A38))
            }
        }
    }
}