package org.example.project.presentation.main.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.presentation.main.models.PostUi

@Composable
fun PostCardItem(
    postUi: PostUi,
    isFirst: Boolean,
    modifier: Modifier = Modifier
) {
    Column {
        if (!isFirst) {
            Spacer(
                modifier = Modifier.height(5.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.secondaryContainer
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
            ) {
                Text(
                    text = postUi.text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                if (postUi.image.isNotEmpty())
                    PostImage(
                        image = postUi.image
                    )

                Spacer(modifier = Modifier.height(10.dp))

                CardReactionsBar(
                    favoriteAmount = postUi.favoriteAmount,
                    commentAmount = postUi.commentAmount,
                    onComment = {},
                    onForward = {},
                    onDislike = {},
                    onLike = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun PostCardItemPrev(){
    PostCardItem(
        postUi = PostUi(
            1,
            "Post text information",
            image = "https://www.reddit.com/r/beards/comments/1rfs9kv/which_beard_look_works_better/#lightbox",
            commentAmount = 2,
            favoriteAmount = 20
        ),
        isFirst = false
    )
}