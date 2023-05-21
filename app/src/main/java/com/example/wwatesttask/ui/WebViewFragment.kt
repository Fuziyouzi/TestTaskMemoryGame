package com.example.wwatesttask.ui

import android.os.Bundle
import android.view.View
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
                bin.webView.loadUrl(it)
                if (!b) {
                    bin.gameBox.visibility = View.GONE
                    bin.webView.visibility = View.VISIBLE
                } else {
                    bin.gameBox.visibility = View.VISIBLE
                    bin.webView.visibility = View.GONE
                }
            }
        }

        vm.config.observeEvent(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(view, "Config is true", Snackbar.LENGTH_LONG).show()
                bin.sitch.isChecked = false
                bin.gameBox.visibility = View.GONE
                bin.webView.visibility = View.VISIBLE
            } else {
                Snackbar.make(view, "Config is false", Snackbar.LENGTH_LONG).show()
                bin.sitch.isChecked = true
                bin.gameBox.visibility = View.VISIBLE
                bin.webView.visibility = View.GONE
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