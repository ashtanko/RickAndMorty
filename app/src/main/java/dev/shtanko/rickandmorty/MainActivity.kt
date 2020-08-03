package dev.shtanko.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import dev.shtanko.core.navigation.AppNavigation
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appNavigation: AppNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        RickAndMorty.applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
        setUpActionBar()
    }

    override fun onDestroy() {
        appNavigation.unbind()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        return getNavigationController().navigateUp()
    }

    private fun setUpActionBar() {
        setupActionBarWithNavController(getNavigationController())
    }

    private fun setUpNavigation() {
        appNavigation.bind(getNavigationController())
    }

    private fun getNavigationController() =
        Navigation.findNavController(this, R.id.nav_host_fragment)
}
