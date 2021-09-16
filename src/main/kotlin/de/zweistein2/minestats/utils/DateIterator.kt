package de.zweistein2.minestats.utils

import java.time.LocalDate

class DateIterator(first: LocalDate, last: LocalDate, private val step: Long, private val limit: Long, private val timeUnit: TimeUnit): Iterator<LocalDate> {
    private val finalElement = last
    private var unitsPast = 1L
    private var hasNext: Boolean = if (step > 0) { first <= last && unitsPast <= limit } else { first >= last && unitsPast <= limit }
    private var next = if (hasNext) first else finalElement

    override fun hasNext(): Boolean = hasNext

    override fun next(): LocalDate {
        val value = next

        if (value == finalElement || unitsPast == limit) {
            if (!hasNext) throw kotlin.NoSuchElementException()
            hasNext = false
        } else {
            when {
                timeUnit == TimeUnit.DAYS -> next = next.plusDays(step)
                timeUnit == TimeUnit.WEEKS -> next = next.plusWeeks(step)
                timeUnit == TimeUnit.MONTHS -> next = next.plusMonths(step)
                timeUnit == TimeUnit.YEARS -> next = next.plusYears(step)
            }

            unitsPast++
        }

        return value
    }
}