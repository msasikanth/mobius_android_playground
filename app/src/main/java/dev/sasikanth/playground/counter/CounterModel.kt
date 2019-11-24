package dev.sasikanth.playground.counter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CounterModel(
    val value: Int
) : Parcelable {

    companion object {
        val ZERO = CounterModel(0)
    }

    fun increment(): CounterModel =
        copy(value = value + 1)

    fun decrement(): CounterModel =
        copy(value = value - 1)
}
