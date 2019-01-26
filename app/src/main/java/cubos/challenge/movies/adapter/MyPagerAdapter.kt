package cubos.challenge.movies.adapter

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cubos.challenge.movies.*
import cubos.challenge.movies.config.*
import cubos.challenge.movies.factory.MovieListFragment

class MyPagerAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(index: Int) = MovieListFragment(index)

    override fun getCount() = CURRENT_TAB_COUNT

    override fun getPageTitle(position: Int) = when (position) {
        ACTION_TAB_INDEX -> context.resources.getString(R.string.tab_title_action)
        DRAMA_TAB_INDEX -> context.resources.getString(R.string.tab_title_drama)
        FANTASY_TAB_INDEX -> context.resources.getString(R.string.tab_title_fantasy)
        FICTION_TAB_INDEX -> context.resources.getString(R.string.tab_title_fiction)
        else -> throw IllegalArgumentException("This Fragment index does not exist")
    }

}