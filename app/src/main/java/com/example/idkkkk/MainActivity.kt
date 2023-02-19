package com.example.idkkkk


import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.idkkkk.destinations.AddPersonListDestination
import com.example.idkkkk.destinations.vPersonListDestination
import com.example.idkkkk.model.Contact
import com.example.idkkkk.ui.AddContactViewModel
import com.example.idkkkk.ui.ContactsListViewMoedel
import com.example.idkkkk.ui.theme.IdkkkkTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IdkkkkTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}


@Destination(start = true)
@Composable
fun PersonListScreen(
    nav: DestinationsNavigator,
    viewModel: ContactsListViewMoedel = hiltViewModel()
) {
    val colorList: List<Color> =
        listOf(Color(182, 156, 255, 255),Color(145, 244, 143, 255),Color(253, 153, 255, 255),Color(255, 245, 153, 255),Color(182, 156, 255, 255),Color(255, 245, 153, 255), Color(158, 255, 255, 255), Color(255, 245, 153, 255))
    val randomColor = colorList.random()
    var i = 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(37, 37, 37, 255)),


    )
    {
        Box(contentAlignment = Alignment.TopStart) {
            Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) {
                Text(
                    text = "Notes",
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 20.dp, 0.dp),
                    color = Color.White,
                    fontSize = 50.sp
                )
            }
        }




       /* Image(
            painter = painterResource(id = R.drawable.rafiki),
            contentDescription = null,
            Modifier
                .padding(13.dp, 100.dp, 0.dp, 0.dp)
                .size(240.dp)

        )*/
       /* Box(contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.padding(80.dp, 0.dp, 40.dp, 40.dp)) {
                Text(
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 0.dp, 200.dp),
                    text = "Create your first note !",
                    color = Color.White

                )




            }
        }*/
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 50.dp, 16.dp, 0.dp)

    ) {



            items(viewModel.contacts) { person ->
            Column(


                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)

                    .background(shape = RoundedCornerShape(30.dp), color =colorList[i++])
                    .clickable {
                        //viewModel.removeContact(person)
                        nav.navigate(vPersonListDestination(person))

                    }
            ) {

                Spacer(modifier = Modifier.height(8.dp))
                Text(modifier = Modifier.padding(4.dp),text = " ${person.name}", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
               // Text(text = " ${person.phone}")

                Box(contentAlignment = Alignment.BottomCenter ) {
                    Column(modifier = Modifier.padding(280.dp, 0.dp, 0.dp, 10.dp)) {
                        IconButton(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(255, 0, 0, 255)),
                            onClick = { viewModel.removeContact(person) },
                        ) {
                            Icon(painter = painterResource(id = R.drawable.delete), contentDescription =null)


                        }

                    }
                }


            }
        }
    }
    Box(contentAlignment = Alignment.Center ) {
        Column(modifier = Modifier.padding(330.dp, 640.dp, 0.dp, 0.dp)) {
            IconButton(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(88, 87, 87, 255)),
                onClick = { nav.navigate(AddPersonListDestination) },
            ) {
                Text(
                    text = "+", color = Color.White
                )


            }

        }
    }}

@Destination
@Composable
fun vPersonList(
    c: Contact,
    navigator: DestinationsNavigator
) {

    Column(
        modifier = Modifier
            .padding(0.dp, 30.dp, 0.dp, 0.dp)
            .fillMaxSize()
            .background(Color(37, 37, 37, 255)),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
//           val textState1 = remember { mutableStateOf(TextFieldValue()) }
            TextField(
                modifier = Modifier

                    .size(500.dp, 80.dp),
                value = c.name,

                onValueChange = { c.name = it },
                textStyle = TextStyle.Default.copy(fontSize = 47.sp),
                colors = TextFieldDefaults.textFieldColors(textColor = Color.White,),

                placeholder = { Text(text = "Title", color = Color.Gray, fontSize = 50.sp) },
            )

        }
        Row {
//            val textState2 = remember { mutableStateOf(TextFieldValue()) }
            c.phone?.let {
                TextField(
                    modifier = Modifier
                        .padding(0.dp)
                        .size(500.dp, 200.dp),
                    value = it,
                    onValueChange = { c.phone = it },
                    textStyle = TextStyle.Default.copy(fontSize = 17.sp),
                    colors = TextFieldDefaults.textFieldColors(textColor = Color.White),
                    placeholder = {
                        Text(
                            text = "Type Something...",
                            color = Color.Gray,
                            fontSize = 20.sp
                        )
                    },

                    )
            }
        }
    }
}


@Destination
@Composable
fun AddPersonList(
    navigator: DestinationsNavigator,
    viewModel: AddContactViewModel = hiltViewModel()
) {
    val textState1 = remember { mutableStateOf(TextFieldValue()) }
    val textState2 = remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier
            .padding(0.dp, 30.dp, 0.dp, 0.dp)
            .fillMaxSize()
            .background(Color(37, 37, 37, 255)),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
//           val textState1 = remember { mutableStateOf(TextFieldValue()) }
            TextField(
                modifier = Modifier

                    .size(500.dp, 80.dp),
                value = textState1.value,
                onValueChange = { textState1.value = it },
                colors = TextFieldDefaults.textFieldColors(textColor = Color.White),

                placeholder = { Text(text = "Title", color = Color.White, fontSize = 20.sp) },
            )

        }
        Row {
//            val textState2 = remember { mutableStateOf(TextFieldValue()) }
            TextField(
                modifier = Modifier
                    .padding(0.dp)
                    .size(500.dp, 200.dp),
                value = textState2.value,
                onValueChange = { textState2.value = it },

                colors = TextFieldDefaults.textFieldColors(textColor = Color.White),
                placeholder = {
                    Text(
                        text = "Type Somthing",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                },

                )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(onClick = {
                viewModel.addContact(
                    textState1.value.text,
                    textState2.value.text
                ); navigator.popBackStack()
            }) {
                Modifier
                    .padding(190.dp, 600.dp, 0.dp, 10.dp)
                    .size(50.dp)
                Text(
                    text = "ADD"
                )
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IdkkkkTheme {

    }
}

