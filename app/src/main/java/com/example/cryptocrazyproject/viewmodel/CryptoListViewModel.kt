package com.example.cryptocrazyproject.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocrazyproject.model.CryptoListItem
import com.example.cryptocrazyproject.repository.CryptoRepository
import com.example.cryptocrazyproject.repository.CryptoRepositoryInterface
import com.example.cryptocrazyproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Closeable
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    val repository: CryptoRepositoryInterface
) : ViewModel() {

    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)


    private var initialCryptoList = listOf<CryptoListItem>()
    private var isSearchStarting = true

    init {
        loadCryptos()
    }

    fun searchCryptoList(query : String) {
        val listToSearch = if (isSearchStarting){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default){
            if (query.isEmpty()){
                cryptoList.value = initialCryptoList
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.currency.contains(query.trim(),ignoreCase = true)
            }

            if (isSearchStarting){
                initialCryptoList = cryptoList.value
                isSearchStarting = false
            }

            cryptoList.value = results
        }
    }

     fun loadCryptos(){

         viewModelScope.launch {
             isLoading.value = true
             val result = repository.getCryptoList()
             when(result) {
                 is Resource.Error -> {
                    errorMessage.value = result.message!!
                     isLoading.value = false
                 }
                 is Resource.Loading -> {
                 }
                 is Resource.Success -> {
                     val cryptoItems = result.data!!.mapIndexed{ index, cryptoListItem ->
                         CryptoListItem(cryptoListItem.currency,cryptoListItem.price)
                     } as List<CryptoListItem>

                     errorMessage.value = ""
                     isLoading.value = false
                     cryptoList.value += cryptoItems

                 }
             }
         }


    }

}