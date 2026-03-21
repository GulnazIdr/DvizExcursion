package org.example.project.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.feature.auth.domain.AppAuthHandler
import org.example.project.feature.auth.domain.token.TokenDataRepository

class AuthViewModel(
    private val appAuthHandler: AppAuthHandler,
    private val tokenDataRepository: TokenDataRepository,
    private val httpClient: HttpClient
) : ViewModel() {
    private val _tokenUiState = MutableStateFlow(
        TokenUiState(
            input = "",
            errorMessage = "",
            isSaved = false,
            isLoading = false
        )
    )
    val tokenUiState: StateFlow<TokenUiState> = _tokenUiState.asStateFlow()

    fun onUrlChanged(url: String) {
        _tokenUiState.update { state ->
            state.copy(
                input = url,
                errorMessage = if (url.isEmpty()) "paste the url" else ""
            )
        }
        if (url.isNotEmpty()) {
            handle()
        }
    }

    fun openLoginPage() {
        viewModelScope.launch {
            runCatching {
                appAuthHandler.open()
            }
        }
    }
    private fun handle() {
        _tokenUiState.update { state ->
            state.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            val res = appAuthHandler.handlePastedUrl(_tokenUiState.value.input, httpClient)
            res.onSuccess { tokenPair ->
                _tokenUiState.update { state ->
                    state.copy(
                        isSaved = tokenDataRepository.saveAccessToken(tokenPair.second) &&
                                tokenDataRepository.saveRefreshToken(
                                    tokenPair.first
                                ),
                        errorMessage = "",
                        input = state.input
                    )
                }
            }.onFailure { error ->
                _tokenUiState.update { state ->
                    state.copy(
                        isSaved = false,
                        errorMessage = error.message.orEmpty(),
                        input = ""
                    )
                }
            }

            _tokenUiState.update { state ->
                state.copy(
                    isLoading = false
                )
            }
        }
    }
}