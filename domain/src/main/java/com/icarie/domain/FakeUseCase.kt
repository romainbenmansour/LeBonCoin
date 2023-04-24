package com.icarie.domain

interface FakeUseCase {
    operator fun invoke(): Boolean
}

class DefaultFakeUseCase : FakeUseCase {
    override fun invoke(): Boolean = false
}
