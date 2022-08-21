package com.kuluruvineeth.epifiassessment.panValidator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kuluruvineeth.epifiassessment.common.CommonViewModel
import com.kuluruvineeth.epifiassessment.utils.PanValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PanValidatorViewModel @Inject constructor(
    private val panValidator: PanValidator
) : CommonViewModel(){

    val isValidPan: MutableLiveData<Boolean> = MutableLiveData()
    val isValidYear: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isValidPan.postValue(false)
        isValidYear.postValue(false)
    }

    fun validatePanCard(panData: String){
        viewModelScope.launch {
            isValidPan.postValue(panValidator.validatePan(panData))
        }
    }

    fun validateBirthYear(year: String){
        viewModelScope.launch {
            if(year.isNotEmpty() && year.toInt() in 1901..2998){
                isValidYear.postValue(true)
            }else{
                isValidYear.postValue(false)
            }
        }
    }
}