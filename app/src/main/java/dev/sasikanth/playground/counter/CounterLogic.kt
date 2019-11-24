package dev.sasikanth.playground.counter

import com.spotify.mobius.Next
import com.spotify.mobius.Next.dispatch
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object CounterLogic : Update<CounterModel, CounterEvent, CounterEffect> {

    override fun update(
        model: CounterModel,
        event: CounterEvent
    ): Next<CounterModel, CounterEffect> {
        when (event) {
            CounterEvent.Increment -> {
                return next(model.increment())
            }
            CounterEvent.Decrement -> {
                return if (model.value == 0) {
                    dispatch(setOf(CounterEffect.BelowZero))
                } else {
                    next(model.decrement())
                }
            }
        }
    }
}