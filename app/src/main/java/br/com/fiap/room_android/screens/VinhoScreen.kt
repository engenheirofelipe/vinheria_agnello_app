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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.fiap.room_android.R
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.room_android.model.Vinho
import br.com.fiap.room_android.repository.VinhoRepository


@Composable
fun VinhoScreen(navController: NavController){
    var nomeVinhoState = remember { mutableStateOf("") }
    var marcaState = remember { mutableStateOf("") }
    var paisOrigemState = remember { mutableStateOf("") }
    var tempoEnvelhecimentoState = remember { mutableStateOf("") }
    var tipoState = remember { mutableStateOf("") }
    var ocasiaoState = remember { mutableStateOf("") }

    val context = LocalContext.current
    val vinhoRepository = VinhoRepository(context)

    var listaVinhosState = remember {
        mutableStateOf(vinhoRepository.listar())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                ){
                    VinhoForm(
                        nome = nomeVinhoState.value,
                        marca = marcaState.value,
                        paisOrigem = paisOrigemState.value,
                        tempoEnvelhecimento = tempoEnvelhecimentoState.value,
                        tipo = tipoState.value,
                        ocasiao = ocasiaoState.value,
                        onNomeChange = { nomeVinhoState.value = it },
                        onMarcaChange = { marcaState.value = it },
                        onPaisOrigemChange = { paisOrigemState.value = it },
                        onTempoEnvelhecimentoChange = { tempoEnvelhecimentoState.value = it },
                        onOcasiaoChange = { ocasiaoState.value = it },
                        onTipoChange = { tipoState.value = it},
                        atualizar = {
                            listaVinhosState.value = vinhoRepository.listar()
                        },
                        navController = navController
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VinhoForm(
    nome:String,
    marca:String,
    paisOrigem:String,
    tempoEnvelhecimento:String,
    tipo:String,
    ocasiao:String,
    onNomeChange: (String) -> Unit,
    onMarcaChange: (String) -> Unit,
    onPaisOrigemChange: (String) -> Unit,
    onTempoEnvelhecimentoChange: (String) -> Unit,
    onTipoChange: (String) -> Unit,
    onOcasiaoChange: (String) -> Unit,
    atualizar: () -> Unit,
    navController: NavController
){
    val contexto = LocalContext.current
    val contatoRepository = VinhoRepository(contexto)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "Cadastrar Vinho",
            color = Color(0xFF8C2A38),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { onNomeChange(it) },
            label = { Text("Nome") },
            modifier = Modifier
                .fillMaxWidth()
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = tempoEnvelhecimento,
            onValueChange = { onTempoEnvelhecimentoChange(it) },
            label = { Text("Tempo de envelhecimento") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = tipo,
            onValueChange = { onTipoChange(it) },
            label = { Text("Tipo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = ocasiao,
            onValueChange = { onOcasiaoChange(it) },
            label = { Text("Ocasiao") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Button(
                onClick = {
                    val vinho = Vinho(
                        nome = nome,
                        marca = marca,
                        paisOrigem = paisOrigem,
                        tempoEnvelhecimento = tempoEnvelhecimento,
                        tipo = tipo,
                        ocasiao = ocasiao
                    )
                    contatoRepository.salvar(vinho = vinho)
                    atualizar()

                    onNomeChange("")
                    onMarcaChange("")
                    onPaisOrigemChange("")
                    onTempoEnvelhecimentoChange("")
                    onTipoChange("")
                    onOcasiaoChange("")
                },
                modifier = Modifier.fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8C2A38))
            ) {
                Text(text = "Cadastrar", color = Color.White)
            }
            Button(
                onClick = {
                    navController.navigate("menu")
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF))
            ) {
                Text(text = "Voltar", color = Color(0xFF8C2A38))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VinhoScreenPreview() {
    VinhoScreen(navController = rememberNavController())
}