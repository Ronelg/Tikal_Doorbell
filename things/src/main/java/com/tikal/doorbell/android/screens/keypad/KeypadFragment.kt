package com.tikal.doorbell.android.screens.keypad


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tikal.doorbell.android.BasePresenter
import com.tikal.doorbell.android.R
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 *
 */
class KeypadFragment : android.support.v4.app.Fragment(), KeypadContract.View {

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"
    }

    private val presenter: KeypadPresenter = KeypadPresenter()

    override fun setPresenter(presenter: BasePresenter) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.i("onCreateView")

        presenter.subscribe(this)
        return inflater.inflate(R.layout.keypad, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
