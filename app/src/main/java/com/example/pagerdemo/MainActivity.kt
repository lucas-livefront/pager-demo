@file:OptIn(ExperimentalFoundationApi::class)

package com.example.pagerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pagerdemo.ui.theme.PagerDemoTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    private val pages: List<PageState> = listOf(
        PageState(
            title = "Page One",
            message = "Message one",
        ),
        PageState(
            title = "Page Two",
            message = "Message two",
        ),
        PageState(
            title = "Page Three",
            message = "Message three",
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagerDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Pager(
                        pages = pages,
                    )
                }
            }
        }
    }
}

@Composable
private fun Pager(
    pages: List<PageState>,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState { pages.size },
) {
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 32.dp),
        key = { index -> pages[index].id },
        pageSpacing = 12.dp,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.padding(bottom = 32.dp),
    ) { pageNumber ->
        Page(
            state = pages[pageNumber],
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

data class PageState(
    val title: String,
    val message: String,
) {
    val id: UUID = UUID.randomUUID()
}

@Composable
private fun Page(
    state: PageState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Text(
            text = state.title,
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
        )

        Spacer(
            modifier = Modifier.height(32.dp),
        )

        Text(
            text = state.message,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
        )

        Spacer(
            modifier = Modifier.height(32.dp),
        )
    }
}