package com.icarie.data.common

import android.content.Context
import com.icarie.domain.utils.StringRetriever
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringRetrieverImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : StringRetriever {

    override fun getString(int: Int, vararg args: Any): String =
        context.resources.getString(int, *args)

    override fun getQuantityString(int: Int, quantity: Int, vararg args: Any): String =
        context.resources.getQuantityString(int, quantity, *args)
}
