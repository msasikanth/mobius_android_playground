package dev.sasikanth.playground.counter

import com.google.android.material.snackbar.Snackbar
import com.spotify.mobius.Update
import com.spotify.mobius.functions.Consumer
import dev.sasikanth.playground.BaseActivity
import dev.sasikanth.playground.R
import dev.sasikanth.playground.counter.effecthandlers.CounterEffectHandlers
import dev.sasikanth.playground.counter.view.CounterViewActions
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class CounterActivity : BaseActivity<CounterModel, CounterEvent, CounterEffect>(),
    CounterViewActions {

    override fun layoutResId(): Int = R.layout.activity_main

    override fun eventDispatcher(output: Consumer<CounterEvent>) {
        counterIncrement.setOnClickListener { output.accept(CounterEvent.Increment) }
        counterDecrement.setOnClickListener { output.accept(CounterEvent.Decrement) }
    }

    override fun render(model: CounterModel) {
        counterText.text = model.value.toString()
    }

    override fun initialModel(): CounterModel = CounterModel.ZERO

    override fun update(): Update<CounterModel, CounterEvent, CounterEffect> = CounterLogic

    override fun effectHandler(): ObservableTransformer<CounterEffect, CounterEvent> =
        CounterEffectHandlers.create(this, AndroidSchedulers.mainThread())

    override fun cleanUp() {
        counterIncrement.setOnClickListener(null)
        counterDecrement.setOnClickListener(null)
    }

    override fun counterBelowZero() {
        val snackbar = Snackbar.make(
            counterRootView,
            "Counter can't go below zero",
            Snackbar.LENGTH_SHORT
        )
        snackbar.setAction(android.R.string.ok) {
            snackbar.dismiss()
        }.show()
    }
}
