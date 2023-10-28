package com.example.githubrepolistingapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubrepolistingapp.component.ErrorDisplay
import com.example.githubrepolistingapp.component.RepoListItem
import com.example.githubrepolistingapp.service.GithubApiBuilder

@Composable
fun GithubRepoListingAppHomeContent(bottomPadding: PaddingValues) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.padding(bottom = bottomPadding.calculateBottomPadding())
    ) {
        items(GithubApiBuilder.repoResults) { repo ->
            RepoListItem(repo = repo)
        }
    }
    if (GithubApiBuilder.errorMsg != null) ErrorDisplay()
}
