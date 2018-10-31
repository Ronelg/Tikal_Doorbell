package com.tikal.doorbell.android.screens.keypad


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tikal.doorbell.android.R
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class KeypadFragment : android.support.v4.app.Fragment() {

    companion object {
        const val TAG = "KeypadFragment"
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.i( "onCreateView: ")
        return inflater.inflate(R.layout.keypad, container, false) as ViewGroup
    }


}
