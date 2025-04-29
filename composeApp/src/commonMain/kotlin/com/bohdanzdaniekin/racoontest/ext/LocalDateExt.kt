package com.bohdanzdaniekin.racoontest.ext

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

fun LocalDate.Companion.now(
    clock: Clock = Clock.System,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDate = clock.now().toLocalDateTime(timeZone).date

fun LocalDate.Companion.fromEpochMillis(
    epochMillis: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDate {
    val instant = Instant.fromEpochMilliseconds(epochMillis)
    return instant.toLocalDateTime(timeZone).date
}

fun LocalDate.toEpochMillis(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Long = atStartOfDayIn(timeZone).toEpochMilliseconds()