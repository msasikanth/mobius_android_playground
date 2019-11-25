package dev.sasikanth.playground.counter.effecthandlers

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import dev.sasikanth.playground.counter.CounterEffect
import dev.sasikanth.playground.counter.view.CounterViewActions
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class CounterEffectHandlersTest {

    @Test
    fun `when the below zero effect is received, the counter below zero snackbar must be shown`() {
        val effectSubject = PublishSubject.create<CounterEffect>()

        val uiActions = mock<CounterViewActions>()

        val disposable = effectSubject
            .compose(CounterEffectHandlers.create(uiActions, Schedulers.trampoline()))
            .subscribe()

        effectSubject.onNext(CounterEffect.BelowZero)

        verify(uiActions).counterBelowZero()
        verifyNoMoreInteractions(uiActions)

        disposable.dispose()
    }
}
