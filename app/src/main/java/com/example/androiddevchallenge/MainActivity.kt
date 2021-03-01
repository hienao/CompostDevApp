/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.text.Layout
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.entity.DogEntity
import com.example.androiddevchallenge.ui.entity.DogEntityPreview
import com.example.androiddevchallenge.ui.remote.DogDataViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var dogDataViewModel =DogDataViewModel()
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "DogList")
                    },
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    elevation = 12.dp
                )
            },
            content = {
                var dogDataViewModel =DogDataViewModel()
                dogDataViewModel.refreshDogData()
                dogDataViewModel.myData.observeAsState().value?.let {
                    DogList(list = it)
                }

            })

    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
@Composable
fun DogList(list: MutableList<DogEntity>) {
    LazyColumn{
        items(list) { dog ->
            DogListItem(dog)

        }
    }
}
@Preview(name = "Dog List",widthDp = 720,heightDp = 30)
@Composable
fun DogListItem(@PreviewParameter(provider = DogEntityPreview::class)dog:DogEntity){
    val padding = 16.dp
    Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Start,
    modifier = Modifier
        .padding(padding)
        .fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),contentDescription = null,modifier = Modifier.requiredWidth(80.dp)
            .height(80.dp))
        Column {
            Text(dog.name)
        }
    }
}