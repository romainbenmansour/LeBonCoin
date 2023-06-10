package com.icarie.base.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.icarie.base.ui.compose.config.AppShapes
import com.icarie.base.ui.compose.config.AppSpacings
import com.icarie.base.ui.compose.config.AppTheme

@Composable
private fun Theme(
    modifier: Modifier = Modifier,
    applyStatusBarPadding: Boolean,
    applySystemBarPadding: Boolean,
    applyNavigationBarsPadding: Boolean,
    content: @Composable BoxScope.() -> Unit,
) {
    AppTheme {
        Box(
            modifier = modifier
                .let { if (applyStatusBarPadding) it.statusBarsPadding() else it }
                .let { if (applySystemBarPadding) it.systemBarsPadding() else it }
                .let { if (applyNavigationBarsPadding) it.navigationBarsPadding() else it }
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UIScreen(
    applyStatusBarPadding: Boolean = false,
    applySystemBarPadding: Boolean = false,
    applyNavigationBarsPadding: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    Theme(
        modifier = Modifier
            .fillMaxSize()
            .semantics { testTagsAsResourceId = true },
        applyStatusBarPadding = applyStatusBarPadding,
        applySystemBarPadding = applySystemBarPadding,
        applyNavigationBarsPadding = applyNavigationBarsPadding,
        content = content
    )
}

@Composable
fun UIBottomSheetScreen(
    applyStatusBarPadding: Boolean = false,
    applySystemBarPadding: Boolean = false,
    applyNavigationBarsPadding: Boolean = false,
    shape: RoundedCornerShape = AppShapes.bottomSheet,
    content: @Composable BoxScope.() -> Unit,
) {
    Theme(
        modifier = Modifier
            .clip(shape)
            .background(MaterialTheme.colors.surface),
        applyStatusBarPadding = applyStatusBarPadding,
        applySystemBarPadding = applySystemBarPadding,
        applyNavigationBarsPadding = applyNavigationBarsPadding,
        content = bottomSheetContent(content = content)
    )
}

private fun bottomSheetContent(
    content: @Composable BoxScope.() -> Unit
): @Composable (BoxScope.() -> Unit) = {
    BoxWithConstraints {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = maxHeight - AppSpacings.statusBar - AppSpacings.XS),
            content = content
        )
    }
}
