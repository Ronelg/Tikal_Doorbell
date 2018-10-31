package com.tikal.doorbell.android.screens.keypad


import android.os.Bundle
import android.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tikal.doorbell.android.BasePresenter
import com.tikal.doorbell.android.BaseView
import com.tikal.doorbell.android.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class KeypadFragment : android.support.v4.app.Fragment(), BaseView {

    lateinit var presenter :KeypadPresenter

    companion object {
        const val TAG = "KeypadFragment"
    }

    init {
    }

    override fun setPresenter(presenter: BasePresenter) {
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        presenter = KeypadPresenter()
        presenter.subscribe(this)
        Log.i(TAG, "onCreateView: ")
        return inflater.inflate(R.layout.keypad, container, false) as ViewGroup
    }


}
