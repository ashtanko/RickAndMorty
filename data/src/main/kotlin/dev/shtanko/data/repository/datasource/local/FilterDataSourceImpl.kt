package dev.shtanko.data.repository.datasource.local

import android.content.Context
import com.google.gson.Gson
import dev.shtanko.core.App
import dev.shtanko.data.repository.datasource.FilterDataSource
import dev.shtanko.domain.model.Filter
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class FilterDataSourceImpl @Inject constructor(
    context: App,
    private val gson: Gson
) : FilterDataSource {

    private val sharedPreferences =
        context.getApplicationContext().getSharedPreferences("Filter", Context.MODE_PRIVATE)

    companion object {
        const val PREFERENCE_KEY = "currentFilter"
    }

    private val subject: Subject<Filter?> = BehaviorSubject.createDefault(getCurrentFiler())

    override fun saveFilter(filter: Filter) {
        saveCurrentFilter(filter)
        subject.onNext(filter)
    }

    override fun getFilter(): Observable<Filter> {
        return subject.map {
            it
        }
    }

    private fun saveCurrentFilter(filter: Filter) {
        sharedPreferences
            .edit()
            .putString(PREFERENCE_KEY, gson.toJson(filter))
            .apply()
    }

    private fun getCurrentFiler(): Filter {
        return sharedPreferences.getString(PREFERENCE_KEY, null)?.let {
            gson.fromJson(it, Filter::class.java)
        } ?: Filter.empty()
    }
}
