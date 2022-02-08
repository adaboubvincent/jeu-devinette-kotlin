package tg.adabounou_kodjovi_vincent.devin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PseudoNumberViewModel: ViewModel() {
    public var number: MutableLiveData<Int> = MutableLiveData<Int>()
    public var randomNimber: MutableLiveData<Int> = MutableLiveData<Int>()
    public var pseudo: MutableLiveData<String> = MutableLiveData<String>()


    fun getNumber(): LiveData<Int> {
        return number
    }

    fun setNumber(n: Int) {
        number.value = n

    }

    fun getRandomNimber(): LiveData<Int> {
        return randomNimber
    }

    fun setRandomNimber(n: Int) {
        randomNimber.value = n

    }

    fun getPseudo(): LiveData<String> {
        return pseudo
    }

    fun setPseudo(n: String) {
        pseudo.value = n

    }

}