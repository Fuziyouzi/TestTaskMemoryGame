package com.example.wwatesttask.ui

import android.os.Bundle
import android.view.View
import com.example.wwatesttask.R
import com.example.wwatesttask.databinding.SplashFragmentBinding
import com.example.wwatesttask.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding>(
    SplashFragmentBinding::inflate
), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            delay(3000)
            withContext(Dispatchers.Main) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, WebViewFragment()).commit()
            }
        }

    }


}