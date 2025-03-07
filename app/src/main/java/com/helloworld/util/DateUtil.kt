package com.helloworld.util

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaLocalTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun Long.toLocalDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    Instant.fromEpochMilliseconds(this).toLocalDateTime(timeZone)

fun Long.toLocalDate(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate =
    Instant.fromEpochMilliseconds(this).toLocalDateTime(timeZone).date

fun Long.toLocalTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalTime =
    Instant.fromEpochMilliseconds(this).toLocalDateTime(timeZone).time

fun LocalDateTime.millis(timeZone: TimeZone = TimeZone.currentSystemDefault()): Long =
    toInstant(timeZone).toEpochMilliseconds()

fun LocalDate.asLocalDateTime() = LocalDateTime(year, month, dayOfMonth, 0, 0)

fun LocalDate.millisAtZeroHour(timeZone: TimeZone = TimeZone.currentSystemDefault()): Long =
    asLocalDateTime().millis(timeZone)

fun LocalTime.millis(): Long =
    ( hour.toDuration(DurationUnit.HOURS) + minute.toDuration(DurationUnit.MINUTES) + second.toDuration(DurationUnit.SECONDS)).toLong(DurationUnit.MILLISECONDS)

fun Long.toLocalTime(): LocalTime {
    val duration = this.toDuration(DurationUnit.MILLISECONDS)
    val hours = duration.inWholeHours
    val minutes = duration.minus(hours.toDuration(DurationUnit.HOURS)).inWholeMinutes
    val seconds = duration.minus(hours.toDuration(DurationUnit.HOURS)).minus(minutes.toDuration(DurationUnit.MINUTES)).inWholeSeconds
    return LocalTime(hours.toInt(), minutes.toInt(), seconds.toInt())
}


fun LocalDateTime.atZeroHours(): LocalDateTime =
    LocalDateTime(year, month, dayOfMonth, 0, 0)

fun LocalDateTime.at24Hours(): LocalDateTime =
    LocalDateTime(year, month, dayOfMonth, 23, 59, 59)

fun LocalDateTime.format(pattern: String = "dd MMM yyyy, hh:mm a"): String {
    return DateTimeFormatter
        .ofPattern(pattern, Locale.getDefault())
        .format(toJavaLocalDateTime())
}

fun LocalDate.format(pattern: String = "dd MMM yyyy"): String {
    return DateTimeFormatter
        .ofPattern(pattern, Locale.getDefault())
        .format(toJavaLocalDate())
}

fun LocalTime.format(pattern: String = "hh:mm a"): String {
    return DateTimeFormatter
        .ofPattern(pattern, Locale.getDefault())
        .format(toJavaLocalTime())
}

fun LocalDateTime.formatPattern(pattern: DatePatterns): String {
    return DateTimeFormatter
        .ofPattern(pattern.pattern, Locale.getDefault())
        .format(this.toJavaLocalDateTime())
}

enum class DatePatterns(val pattern: String) {
    HH_MM_A("hh:mm a"),
    MMMM_YYYY("MMMM yyyy"),
    DD_MMMM_YYYY("dd MMMM yyyy"),
    DD_MMM_YYYY("dd MMM yyyy"),
    DD_MM_YYYY("dd/MM/yyyy"),
    DD_MM_YY("dd/MM/yy"),
    MMM_DD_YYYY("MMM dd, yyyy"),
    MMM_DD_YY("MMM dd, yy"),
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    DD_MMM_YYYY_HH_MM("dd MMM yyyy HH:mm"),
    DD_MMM_YYYY_HH_MM_SS("dd MMM yyyy HH:mm:ss"),
    DD_MM_YYYY_HH_MM("dd/MM/yyyy HH:mm"),
    DD_MM_YYYY_HH_MM_SS("dd/MM/yyyy HH:mm:ss"),
    MMM_DD_YYYY_HH_MM("MMM dd, yyyy HH:mm"),
    MMM_DD_YYYY_HH_MM_SS("MMM dd, yyyy HH:mm:ss"),
    YYYY_MM_DD_T_HH_MM_SS("yyyy-MM-dd'T'HH:mm:ss"),
    DD_MMM_YYYY_T_HH_MM_SS("dd MMM yyyy'T'HH:mm:ss"),
    DD_MM_YYYY_T_HH_MM_SS("dd/MM/yyyy'T'HH:mm:ss"),
    MMM_DD_YYYY_T_HH_MM_SS("MMM dd, yyyy'T'HH:mm:ss"),
    YYYY_MM_DD_T_HH_MM_SS_SSS("yyyy-MM-dd'T'HH:mm:ss.SSS"),
    DD_MMM_YYYY_T_HH_MM_SS_SSS("dd MMM yyyy'T'HH:mm:ss.SSS"),
    DD_MM_YYYY_T_HH_MM_SS_SSS("dd/MM/yyyy'T'HH:mm:ss.SSS"),
    MMM_DD_YYYY_T_HH_MM_SS_SSS("MMM dd, yyyy'T'HH:mm:ss.SSS"),
}


fun Number.addDecimals(maxDecimals: Int = 2): String {
    return if (!this.toString().contains(".")) {
        var result = this.toInt().toString() + "."
        repeat(maxDecimals) {
            result += "0"
        }
        println("$this -> $result")
        result
    } else {
        val split = this.toString().split(".")
        val decimal = split[1]
        if (decimal.length < maxDecimals) {
            var result = this.toString()
            repeat(maxDecimals - decimal.length) {
                result += "0"
            }
            result
        } else {
            var result = split[0] + "."
            repeat(maxDecimals) {
                result += decimal[it]
            }
            result
        }
    }
}

fun Number.addPrefixZeros(maxDigits: Int = 2): String {
    return if (this.toString().length < maxDigits) {
        var result = this.toString()
        repeat(maxDigits - this.toString().length) {
            result = "0$result"
        }
        result
    } else {
        this.toString()
    }
}

fun Int.makeItPositive() = if (this.toDouble() < 0) -1 * this else this
