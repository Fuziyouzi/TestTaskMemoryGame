package com.example.wwatesttask.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.wwatesttask.R
import com.example.wwatesttask.databinding.GameFragmentBinding
import com.example.wwatesttask.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


@AndroidEntryPoint
class GameFragment : BaseFragment<GameFragmentBinding>(
    GameFragmentBinding::inflate
), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val vm: GameViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var previousImage: ImageView = bin.nullImage

        vm.scoreL.observe(viewLifecycleOwner) {
            bin.score.text = it.toString()
        }
        vm.attemptsL.observe(viewLifecycleOwner) {
            bin.attempts.text = it.toString()
        }


        fun enabledAll() {
            bin.apply {
                a1.isEnabled = true
                a2.isEnabled = true
                a3.isEnabled = true
                a4.isEnabled = true
                a5.isEnabled = true
                b1.isEnabled = true
                b2.isEnabled = true
                b3.isEnabled = true
                b4.isEnabled = true
                b5.isEnabled = true
                c1.isEnabled = true
                c2.isEnabled = true
                c3.isEnabled = true
                c4.isEnabled = true
                c5.isEnabled = true
                d1.isEnabled = true
                d2.isEnabled = true
                d3.isEnabled = true
                d4.isEnabled = true
                d5.isEnabled = true
            }
        }

        fun disableAll() {
            bin.apply {
                a1.isEnabled = false
                a2.isEnabled = false
                a3.isEnabled = false
                a4.isEnabled = false
                a5.isEnabled = false
                b1.isEnabled = false
                b2.isEnabled = false
                b3.isEnabled = false
                b4.isEnabled = false
                b5.isEnabled = false
                c1.isEnabled = false
                c2.isEnabled = false
                c3.isEnabled = false
                c4.isEnabled = false
                c5.isEnabled = false
                d1.isEnabled = false
                d2.isEnabled = false
                d3.isEnabled = false
                d4.isEnabled = false
                d5.isEnabled = false
            }
        }


        fun checkResult(previous: ImageView, current: ImageView, resIm: Int, id: Int) {
            vm.idImL.observe(viewLifecycleOwner) {
                if (!it.click) {
                    vm.clicked(true)
                    vm.changeIdIm(id)
                    previousImage = current
                    current.isEnabled = false
                    current.setImageResource(resIm)
                } else {
                    vm.clicked(false)
                    current.setImageResource(resIm)
                    if (previous != current && it.id == id && previous != bin.nullImage) {
                        vm.upScore()
                        vm.changeIdIm(id)
                        current.visibility = View.GONE
                        previous.visibility = View.GONE
                    } else {
                        disableAll()
                        launch {
                            delay(3000)
                            withContext(Dispatchers.Main) {
                                vm.attemptsTry()
                                current.setImageResource(R.drawable.question_block)
                                previous.setImageResource(R.drawable.question_block)
                            }
                            enabledAll()
                        }
                    }
                }
            }
        }
        vm.listL.observe(viewLifecycleOwner) { list ->

            bin.a1.setOnClickListener {
                checkResult(previousImage, bin.a1, list[0].image, list[0].id)
            }
            bin.a2.setOnClickListener {
                checkResult(previousImage, bin.a2, list[1].image, list[1].id)
            }
            bin.a3.setOnClickListener {
                checkResult(previousImage, bin.a3, list[2].image, list[2].id)
            }
            bin.a4.setOnClickListener {
                checkResult(previousImage, bin.a4, list[3].image, list[3].id)
            }
            bin.a5.setOnClickListener {
                checkResult(previousImage, bin.a5, list[4].image, list[4].id)
            }
            bin.b1.setOnClickListener {
                checkResult(previousImage, bin.b1, list[5].image, list[5].id)
            }
            bin.b2.setOnClickListener {
                checkResult(previousImage, bin.b2, list[6].image, list[6].id)
            }
            bin.b3.setOnClickListener {
                checkResult(previousImage, bin.b3, list[7].image, list[7].id)
            }
            bin.b4.setOnClickListener {
                checkResult(previousImage, bin.b4, list[8].image, list[8].id)
            }
            bin.b5.setOnClickListener {
                checkResult(previousImage, bin.b5, list[9].image, list[9].id)
            }
            bin.c1.setOnClickListener {
                checkResult(previousImage, bin.c1, list[10].image, list[10].id)
            }
            bin.c2.setOnClickListener {
                checkResult(previousImage, bin.c2, list[11].image, list[11].id)
            }
            bin.c3.setOnClickListener {
                checkResult(previousImage, bin.c3, list[12].image, list[12].id)
            }
            bin.c4.setOnClickListener {
                checkResult(previousImage, bin.c4, list[13].image, list[13].id)
            }
            bin.c5.setOnClickListener {
                checkResult(previousImage, bin.c5, list[14].image, list[14].id)
            }
            bin.d1.setOnClickListener {
                checkResult(previousImage, bin.d1, list[15].image, list[15].id)
            }
            bin.d2.setOnClickListener {
                checkResult(previousImage, bin.d2, list[16].image, list[16].id)
            }
            bin.d3.setOnClickListener {
                checkResult(previousImage, bin.d3, list[17].image, list[17].id)
            }
            bin.d4.setOnClickListener {
                checkResult(previousImage, bin.d4, list[18].image, list[18].id)
            }
            bin.d5.setOnClickListener {
                checkResult(previousImage, bin.d5, list[19].image, list[19].id)
            }
        }

        vm.endGameL.observe(viewLifecycleOwner) {
            gameDialog(it)
        }
        parentFragmentManager.popBackStack(WebViewFragment.frag, 0)

    }


    private fun gameDialog(text: String) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.game_dialog)
        dialog.setCancelable(false)
        dialog.show()
        val textDialog = dialog.findViewById<TextView>(R.id.textDialog)
        textDialog.text = text
        val scoreDialog = dialog.findViewById<TextView>(R.id.dialogScore)
        scoreDialog.text = bin.score.text
        val exit = dialog.findViewById<Button>(R.id.exitBtn)
        exit.setOnClickListener {
            parentFragmentManager.popBackStack(WebViewFragment.frag, 0)
            dialog.dismiss()
        }
    }

}





