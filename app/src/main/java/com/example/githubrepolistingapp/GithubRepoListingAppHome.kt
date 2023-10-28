package com.example.githubrepolistingapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GithubRepoListingAppHomeContent(bottomPadding: PaddingValues) {
    val repos = remember { GithubApiBuilder.repoResults }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.padding(bottom = bottomPadding.calculateBottomPadding())
    ) {
        items(GithubApiBuilder.repoResults) { repo ->
            RepoListItem(repo = repo)
        }
    }
}
