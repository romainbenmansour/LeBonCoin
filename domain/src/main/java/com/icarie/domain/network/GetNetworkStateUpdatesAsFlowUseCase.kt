package com.icarie.domain.network

import com.deezer.zen.domain.network.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetNetworkStateUpdatesAsFlowUseCase {
    operator fun invoke(): Flow<NetworkState>
}

class GetNetworkStateUpdatesAsFlowUseCaseImpl @Inject constructor(
    private val networkController: NetworkController
) : GetNetworkStateUpdatesAsFlowUseCase {
    override fun invoke(): Flow<NetworkState> = networkController.networkStateFlow
}
