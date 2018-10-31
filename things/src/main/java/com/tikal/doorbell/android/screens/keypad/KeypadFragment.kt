package com.tikal.doorbell.android.screens.keypad


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tikal.doorbell.android.BasePresenter
import com.tikal.doorbell.android.BaseView
import com.tikal.doorbell.android.R
import kotlinx.android.synthetic.main.keypad.*
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class KeypadFragment : android.support.v4.app.Fragment(), KeypadContract.View {

    lateinit var presenter: KeypadPresenter

    companion object {
        const val TAG = "KeypadFragment"
    }

    init {
    }

    override fun setPresenter(presenter: BasePresenter) {
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.i("onCreateView: ")

        presenter = KeypadPresenter()
        presenter.subscribe(this)
        Timber.i("onCreateView: ")
        setupViews();
        return inflater.inflate(R.layout.keypad, container, false) as ViewGroup
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
        pad_hash_tag.setOnClickListener { presenter.onKeypadNumberClicked("#") }
        pad_star.setOnClickListener { presenter.onKeypadNumberClicked("*") }

    }

    override fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }


    override fun updateEnteredCode(enteredCode: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
