package dev.sasikanth.playground.counter

sealed class CounterEvent {
    object Increment : CounterEvent()
    object Decrement : CounterEvent()
}
