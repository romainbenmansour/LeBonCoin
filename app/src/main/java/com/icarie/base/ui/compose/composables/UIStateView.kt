package com.icarie.base.ui.compose.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.icarie.base.ui.compose.config.AppSpacings
import com.icarie.base.ui.compose.config.iconSecondary
import com.icarie.base.ui.compose.config.textSecondary
import com.icarie.base.ui.compose.states.UIState
import com.icarie.base.R

@Composable
fun <T> UIStateView(
    state: UIState<T>,
    modifier: Modifier = Modifier,
    emptyTitle: String = String(),
    emptyContent: String = String(),
    content: @Composable (T) -> Unit
) {
    when (state) {
        is UIState.Failure ->
            Box(
                modifier = modifier
                    .padding(AppSpacings.M)
            ) {
                UIFailure(failure = state)
            }
        is UIState.Loading ->
            Box(
                modifier = modifier
                    .padding(AppSpacings.M)
            ) {
                UILoading()
            }
        is UIState.None -> Unit
        is UIState.Empty -> Box(
            modifier = modifier
                .padding(AppSpacings.M)
        ) {
            UIEmpty(
                title = emptyTitle,
                content = emptyContent
            )
        }

        is UIState.Success -> content(state.data)
    }
}

@Composable
private fun BoxScope.UILoading() {
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        color = MaterialTheme.colors.iconSecondary
    )
}

@Composable
private fun BoxScope.UIFailure(failure: UIState.Failure<*>) {
    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(AppSpacings.XL),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(failure.error.title),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.textSecondary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(AppSpacings.S))

        Text(
            text = stringResource(failure.error.content),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.textSecondary,
            textAlign = TextAlign.Center,
        )

        failure.onRetry?.let {
            StyledButton(
                text = stringResource(id = R.string.offline_action_retry_mobile),
                onClick = it,
                modifier = Modifier
                    .padding(AppSpacings.M)
            )
        }
    }
}

@Composable
private fun BoxScope.UIEmpty(
    title: String,
    content: String,
) {
    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(AppSpacings.XL),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.textSecondary
        )

        Spacer(Modifier.height(AppSpacings.XS))

        Text(
            text = content,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.textSecondary,
            textAlign = TextAlign.Center
        )
    }
}
