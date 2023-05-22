package com.example.wwatesttask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wwatesttask.domain.model.Images
import com.example.wwatesttask.domain.ListOfImages
import com.example.wwatesttask.ui.base.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val listOfImages: ListOfImages
    ) : ViewModel() {


    private val score: MutableLiveData<Int> = MutableLiveData()
    val scoreL: LiveData<Int> = score

    private val attempts: MutableLiveData<Int> = MutableLiveData()
    val attemptsL: LiveData<Int> = attempts

    private val idIm: MutableLiveData<GameState> = MutableLiveData()
    val idImL: LiveData<GameState> = idIm

    private val list: MutableLiveData<List<Images>> = MutableLiveData()
    val listL: LiveData<List<Images>> = list

    private val endGame: MutableLiveData<String> = MutableLiveData()
    val endGameL: LiveData<String> = endGame

    init {
        setGame()
    }


    fun changeIdIm(id: Int){
        idIm.value?.id = id
    }

    fun clicked(click: Boolean){
        idIm.value?.click = click
    }

    fun upScore() {
        if (score.value == 9){
            endGame.value = "You Win"
        } else {
            score.value = score.value?.plus(1)
        }
    }
    fun lvl(lvl: Int) {
        when (lvl) {
            1 -> attempts.value = 22
            2 -> attempts.value = 16
            else -> attempts.value = 10

        }

    }
    fun attemptsTry() {
        if(attempts.value == 1){
            endGame.value = "You loose"
        } else{
            attempts.value = attempts.value?.minus(1)
        }
    }

   private fun setGame(){
       list.value = listOfImages.getList().shuffled()
       idIm.value = GameState(100, false )
       score.value = 0
       attempts.value = 22
    }


}


