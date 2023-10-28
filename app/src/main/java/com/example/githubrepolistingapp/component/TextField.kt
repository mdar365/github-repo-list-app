package com.example.githubrepolistingapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubrepolistingapp.service.GithubApiBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Composable
fun TxtField() {
    var text by rememberSaveable { mutableStateOf("") }
    val singleThreadedDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    val localScope = CoroutineScope(singleThreadedDispatcher + Job())
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
            },
            placeholder = { Text("Name") }
        )

        Button(
            onClick = {
                localScope.launch {
                    GithubApiBuilder.getRepos(text)
                }
            }
        ) {
            Text(text = "FETCH REPOS")
        }
    }

}