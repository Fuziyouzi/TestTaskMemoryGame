package com.example.wwatesttask.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.example.wwatesttask.R
import com.example.wwatesttask.databinding.WebViewFragmentBinding
import com.example.wwatesttask.ui.base.BaseFragment
import com.example.wwatesttask.ui.base.observeEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment : BaseFragment<WebViewFragmentBinding>(
    WebViewFragmentBinding::inflate
) {

    private val vm: WebViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getUrl()
        vm.getConfig()
        bin.webView.settings.javaScriptEnabled = true

        vm.url.observe(viewLifecycleOwner) {
            bin.webView.loadUrl(it)

            //Added for testing easily could be removed
            bin.sitch.setOnCheckedChangeListener { _, b ->
                if (b) {
                    bin.gameBox.visibility = View.GONE
                    bin.webView.visibility = View.VISIBLE
                    bin.webView.loadUrl("https://www.pinterest.com/search/pins/?q=adnroid&rs=typed")
                } else {
                    bin.gameBox.visibility = View.VISIBLE
                    bin.webView.visibility = View.GONE
                }
            }
        }

        vm.config.observeEvent(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(view, "Config is true", Snackbar.LENGTH_LONG).show()
                bin.gameBox.visibility = View.GONE
                bin.webView.visibility = View.VISIBLE
            } else {
                Snackbar.make(view, "Config is false", Snackbar.LENGTH_LONG).show()
                bin.gameBox.visibility = View.VISIBLE
                bin.webView.visibility = View.GONE
            }
        }
        bin.hardLevel.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.easy ->  setFragmentResult("requestKey", bundleOf("data" to 1))
                R.id.medium ->  setFragmentResult("requestKey", bundleOf("data" to 2))
                R.id.hard -> setFragmentResult("requestKey", bundleOf("data" to 3))

            }
        }


        bin.startGame.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, GameFragment()).addToBackStack(frag)
                .commit()
        }

    }

    companion object {
        const val frag = "FRAG"
    }


}