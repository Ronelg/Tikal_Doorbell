package com.tikal.doorbell.android.screens.keypad


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.tikal.doorbell.android.BasePresenter
import com.tikal.doorbell.android.R
import com.tikal.doorbell.android.R.id.*
import timber.log.Timber
import kotlinx.android.synthetic.main.keypad.*

/**
 * A simple [Fragment] subclass.
 *
 */
class KeypadFragment : android.support.v4.app.Fragment(), KeypadContract.View {


    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
     }

    private val presenter: KeypadPresenter = KeypadPresenter()

    override fun setPresenter(presenter: BasePresenter) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.i("onCreateView")

        return inflater.inflate(R.layout.keypad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe(this)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }


    fun setupViews(){
        pad_1.setOnClickListener { presenter.onKeypadNumberClicked("1") }
        pad_2.setOnClickListener { presenter.onKeypadNumberClicked("2") }
        pad_3.setOnClickListener { presenter.onKeypadNumberClicked("3") }
        pad_4.setOnClickListener { presenter.onKeypadNumberClicked("4") }
        pad_5.setOnClickListener { presenter.onKeypadNumberClicked("5") }
        pad_6.setOnClickListener { presenter.onKeypadNumberClicked("6") }
        pad_7.setOnClickListener { presenter.onKeypadNumberClicked("7") }
        pad_8.setOnClickListener { presenter.onKeypadNumberClicked("8") }
        pad_9.setOnClickListener { presenter.onKeypadNumberClicked("9") }
        pad_0.setOnClickListener { presenter.onKeypadNumberClicked("0") }
    }

    override fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }


    override fun updateEnteredCode(enteredCode: String) {
//        txt_top.text = enteredCode
        Toast.makeText(context, enteredCode, Toast.LENGTH_SHORT).show()
    }


}
