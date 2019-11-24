package dev.sasikanth.playground.counter.effecthandlers

import com.spotify.mobius.rx2.RxMobius
import dev.sasikanth.playground.counter.CounterEffect
import dev.sasikanth.playground.counter.CounterEvent
import dev.sasikanth.playground.counter.view.CounterViewActions
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers

object CounterEffectHandlers {

    fun create(viewActions: CounterViewActions): ObservableTransformer<CounterEffect, CounterEvent> {
        return RxMobius.subtypeEffectHandler<CounterEffect, CounterEvent>()
            .addAction(
                CounterEffect.BelowZero::class.java,
                viewActions::counterBelowZero,
                AndroidSchedulers.mainThread()
            )
            .build()
    }
}
