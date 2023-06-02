package com.icarie.domain.network

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetNetworkStateUpdatesAsFlowUseCase {
    operator fun invoke(): Flow<NetworkState>
}

class GetNetworkStateUpdatesAsFlowUseCaseImpl @Inject constructor(
    private val networkRepository: NetworkRepository
) : GetNetworkStateUpdatesAsFlowUseCase {
    override operator fun invoke(): Flow<NetworkState> = networkRepository.networkStateFlow
}
