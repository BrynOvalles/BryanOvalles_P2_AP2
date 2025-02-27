package edu.ucne.bryanovalles_p2_ap2.presentation.entidad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.bryanovalles_p2_ap2.data.local.remote.Resource
import edu.ucne.bryanovalles_p2_ap2.data.local.remote.dto.DepositoDto
import edu.ucne.bryanovalles_p2_ap2.data.repository.DepositoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DespositoViewModel @Inject constructor(
    private val depositoRepository: DepositoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DepositoUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getDepositos()
    }

    fun save() {
        viewModelScope.launch {
            if (isValid()) {
                depositoRepository.save(_uiState.value.toEntity())
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            depositoRepository.delete(id)
        }
    }

    fun update() {
        viewModelScope.launch {
            depositoRepository.update(
                _uiState.value.idDeposito, DepositoDto(

                )
            )
        }
    }

    fun new() {
        _uiState.value = DepositoUiState()
    }

    fun find(depositoId: Int) {
        viewModelScope.launch {
            if (depositoId > 0) {
                val depositoDto = depositoRepository.find(depositoId)
                if (depositoDto != null) {
                    if (depositoDto.idDeposito != 0) {
                        _uiState.update {
                            it.copy(
                                idDeposito = depositoDto.idDeposito,
                                fecha = depositoDto.fecha,
                                idCuenta = depositoDto.idCuenta,
                                concepto = depositoDto.concepto,
                                monto = depositoDto.monto
                            )
                        }
                    }
                }
            }
        }
    }

    fun getDepositos() {
        viewModelScope.launch {
            DepositoRepository.getDepositos().collectLatest { result ->
                when (result) {
                    is Resource.Loading<*> -> {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resource.Success<*> -> {
                        _uiState.update {
                            it.copy(
                                Depositoes = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error<*> -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = result.message ?: "Error desconocido",
                                isLoading = false
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    fun isValid(): Boolean {
        return uiState.value.concepto.isNotBlank() && uiState.value.fecha.isNotBlank() && uiState.value.monto != null
    }

}

fun DepositoUiState.toEntity() = DepositoDto(
    idCuenta = this.idCuenta,
    fecha = this.fecha,
    idDeposito = this.idDeposito,
    concepto = this.concepto,
    monto = this.monto
)