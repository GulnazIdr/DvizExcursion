package org.example.project.feature.main.presentation.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardReactionsBar(
    favoriteAmount: Int,
    commentAmount: Int,
    onLike: () -> Unit,
    onDislike: () -> Unit,
    onComment: () -> Unit,
    onForward: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CardReactionItem { reactionModifier ->
            FavoriteReaction(
                favoriteAmount = favoriteAmount,
                modifier = reactionModifier,
                onLike = onLike,
                onDislike = onDislike
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        CardReactionItem { reactionModifier ->
            CommentReaction(
                commentAmount = commentAmount,
                modifier = reactionModifier.clickable(onClick = onComment),
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        CardReactionItem { reactionModifier ->
            ForwardReaction(
                modifier = reactionModifier.clickable(onClick = onForward)
            )
        }
    }
}

@Preview
@Composable
private fun CardReactionsBarPrev(){
    CardReactionsBar(
        favoriteAmount = 3,
        commentAmount = 2,
        onComment = {},
        onForward = {},
        onDislike = {},
        onLike = {}
    )
}