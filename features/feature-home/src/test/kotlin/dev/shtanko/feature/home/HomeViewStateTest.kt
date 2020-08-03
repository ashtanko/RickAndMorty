package dev.shtanko.feature.home

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeViewStateTest {

    private lateinit var testee: HomeViewState

    @Test
    fun `set state as refreshing should be settled`() {
        testee = HomeViewState.Refreshing

        assertTrue(testee.isRefreshing())
        assertFalse(testee.isLoaded())
        assertFalse(testee.isLoading())
        assertFalse(testee.isEmpty())
        assertFalse(testee.isError())
        assertFalse(testee.isNoMoreElements())
    }

    @Test
    fun `set state as loaded should be settled`() {
        testee = HomeViewState.Loaded

        assertTrue(testee.isLoaded())
        assertFalse(testee.isRefreshing())
        assertFalse(testee.isLoading())
        assertFalse(testee.isEmpty())
        assertFalse(testee.isError())
        assertFalse(testee.isNoMoreElements())
    }

    @Test
    fun `set state ss loading should be settled`() {
        testee = HomeViewState.Loading

        assertTrue(testee.isLoading())
        assertFalse(testee.isRefreshing())
        assertFalse(testee.isLoaded())
        assertFalse(testee.isEmpty())
        assertFalse(testee.isError())
        assertFalse(testee.isNoMoreElements())
    }
}
