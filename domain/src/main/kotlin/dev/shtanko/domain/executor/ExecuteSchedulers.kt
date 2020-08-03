package dev.shtanko.domain.executor

import io.reactivex.Scheduler

interface ExecuteSchedulers {
    fun io(): Scheduler
    fun main(): Scheduler
}
