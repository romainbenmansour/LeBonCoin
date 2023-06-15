package com.icarie.domain.album

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PaginatedContent<T : Any> {
    val pager: Pager<String, T>
    val flow: Flow<PagingData<T>> get() = pager.flow
}