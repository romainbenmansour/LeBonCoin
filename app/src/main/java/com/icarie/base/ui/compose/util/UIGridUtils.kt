package com.icarie.base.ui.compose.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import com.icarie.base.ui.compose.config.AppSpacings

fun LazyGridScope.fullWidthItem(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(
        span = { GridItemSpan(maxLineSpan) },
        content = content
    )
}

val LazyGridState.hasAllItemsVisible
    @Composable
    get() =
        remember {
            derivedStateOf {
                val firstItemIsVisible =
                    (layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0) == 0
                val lastItemIsVisible = (layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    ?: 0) == layoutInfo.totalItemsCount - 1

                firstItemIsVisible && lastItemIsVisible
            }
        }

@Composable
fun getPaddingForCellAtIndex(
    index: Int,
    columnNb: Int,
    topPadding: Dp = AppSpacings.none
): PaddingValues =
    when (index % columnNb) {
        // Item is in the first column
        0 -> PaddingValues(
            top = topPadding,
            start = AppSpacings.M,
            end = AppSpacings.M / 2
        )
        // Item is in the last column
        columnNb - 1 -> PaddingValues(
            top = topPadding,
            start = AppSpacings.M / 2,
            end = AppSpacings.M
        )
        // Item is in a middle column
        else -> PaddingValues(
            top = topPadding,
            start = AppSpacings.M / 2,
            end = AppSpacings.M / 2
        )
    }