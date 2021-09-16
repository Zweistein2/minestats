package de.zweistein2.minestats.utils

import java.time.LocalDate

class DateProgression(override val start: LocalDate, override val endInclusive: LocalDate, private val step: Long = 1, private val limit: Long = 5, private val timeUnit: TimeUnit): Iterable<LocalDate>, ClosedRange<LocalDate> {
    companion object {
        infix fun LocalDate.downTo(to: LocalDate): DateProgression {
            return fromClosedRange(this, to, -1)
        }

        infix fun DateProgression.step(step: Long): DateProgression {
            checkStepIsPositive(step > 0, step)
            return fromClosedRange(start, endInclusive, if (this.step > 0) step else -step, limit, timeUnit)
        }

        infix fun DateProgression.limit(limit: Long): DateProgression {
            return fromClosedRange(start, endInclusive, step, if (limit == -1L) Long.MAX_VALUE else limit, timeUnit)
        }

        infix fun DateProgression.unit(timeUnit: TimeUnit): DateProgression {
            return fromClosedRange(start, endInclusive, step, limit, timeUnit)
        }

        private fun fromClosedRange(rangeStart: LocalDate, rangeEnd: LocalDate, step: Long, limit: Long = 5, timeUnit: TimeUnit = TimeUnit.DAYS): DateProgression = DateProgression(rangeStart, rangeEnd, step, limit, timeUnit)

        private fun checkStepIsPositive(isPositive: Boolean, step: Number) {
            if (!isPositive) throw IllegalArgumentException("Step must be positive, was: $step.")
        }
    }

    override fun iterator(): Iterator<LocalDate> = DateIterator(start, endInclusive, step, limit, timeUnit)
}