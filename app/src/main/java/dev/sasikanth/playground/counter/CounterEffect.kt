package dev.sasikanth.playground.counter

sealed class CounterEffect {

    object BelowZero : CounterEffect()
}
