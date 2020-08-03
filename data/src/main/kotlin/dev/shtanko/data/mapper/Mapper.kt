package dev.shtanko.data.mapper

interface Mapper<F, T> {
    fun map(from: F): T
}
