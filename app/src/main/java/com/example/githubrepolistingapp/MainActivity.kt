package com.example.githubrepolistingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.githubrepolistingapp.component.TopBar
import com.example.githubrepolistingapp.component.TxtField
import com.example.githubrepolistingapp.service.GithubApiBuilder
import com.example.githubrepolistingapp.ui.theme.GithubRepoListingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubRepoListingAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Scaffold(
        topBar = { TopBar() },
        content = { bottomPadding ->
            println("REPO_LIST: ${GithubApiBuilder.repoResults.size}")
            GithubRepoListingAppHomeContent(bottomPadding)
        },
        bottomBar = { TxtField() }
    )
}
