package dev.sasikanth.playground.counter


import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class CounterLogicTest {

    private val updateSpec = UpdateSpec(CounterLogic::update)

    private val zeroModel = CounterModel.ZERO

    @Test
    fun `counter increment from zero`() {
        updateSpec.given(zeroModel)
            .`when`(CounterEvent.Increment)
            .then(
                assertThatNext(
                    hasModel(zeroModel.increment()),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `counter decrement`() {
        val model = CounterModel(2)
        updateSpec.given(model)
            .`when`(
                CounterEvent.Decrement
            )
            .then(
                assertThatNext(
                    hasModel(model.decrement()),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `counter decrement below zero`() {
        updateSpec.given(zeroModel)
            .`when`(
                CounterEvent.Decrement
            )
            .then(
                assertThatNext(
                    hasEffects(CounterEffect.BelowZero as CounterEffect)
                )
            )
    }
}