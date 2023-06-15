package com.icarie.domain.utils

interface StringRetriever {
    fun getString(int: Int, vararg args: Any): String
    fun getQuantityString(int: Int, quantity: Int, vararg args: Any): String
}
