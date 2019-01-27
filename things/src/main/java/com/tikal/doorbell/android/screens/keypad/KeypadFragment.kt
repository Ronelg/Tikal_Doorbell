package com.tikal.doorbell.android.screens.keypad

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tikal.arch.BasePresenter
import com.tikal.doorbell.android.R
import com.tikal.doorbell.android.utils.RxEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.keypad.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class KeypadFragment : Fragment(), KeypadContract.View {

    private val presenter: KeypadPresenter = KeypadPresenter()

    override fun setPresenter(presenter: BasePresenter) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.v("onCreateView")
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

    @SuppressLint("CheckResult")
    private fun setupViews() {
        pad_1.setOnClickListener { onKeypadNumberClicked("1") }
        pad_2.setOnClickListener { onKeypadNumberClicked("2") }
        pad_3.setOnClickListener { onKeypadNumberClicked("3") }
        pad_4.setOnClickListener { onKeypadNumberClicked("4") }
        pad_5.setOnClickListener { onKeypadNumberClicked("5") }
        pad_6.setOnClickListener { onKeypadNumberClicked("6") }
        pad_7.setOnClickListener { onKeypadNumberClicked("7") }
        pad_8.setOnClickListener { onKeypadNumberClicked("8") }
        pad_9.setOnClickListener { onKeypadNumberClicked("9") }
        pad_0.setOnClickListener { onKeypadNumberClicked("0") }

        RxEditText.fromEditText(etKeyCode)
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: String? ->
                    if (t != null) {
                        presenter.onKeypadNumberClicked(t)
                    }
                }
    }

    private fun onKeypadNumberClicked(number: String) {
        etKeyCode.text.append(number)
    }

    override fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun updateEnteredCode(enteredCode: String) {
        etKeyCode.setText(enteredCode)
    }

    override fun showAccessDenied() {
        toast(getString(R.string.access_denied))
    }

    override fun showAccessGranted() {
        toast(getString(R.string.access_granted))
    }
}
