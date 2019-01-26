package cubos.challenge.movies.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import cubos.challenge.movies.R
import cubos.challenge.movies.adapter.MyPagerAdapter
import cubos.challenge.movies.fragment.MovieListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener, View.OnClickListener {

    private var filteredListFragment: MovieListFragment? = null

    // Activity Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainPagerView.adapter = MyPagerAdapter(this, supportFragmentManager)
        mainTabLayout.setupWithViewPager(mainPagerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_search_bar, menu)

        val menuItem = menu?.findItem(R.id.barSearch)
        val searchView = menuItem?.actionView as SearchView
        val closeButton = searchView.findViewById(R.id.search_close_btn) as ImageView

        menuItem.setOnActionExpandListener(this)
        searchView.setOnQueryTextListener(this)
        closeButton.setOnClickListener(this)

        return true
    }

    // Search View Listener Methods
    override fun onQueryTextSubmit(query: String?): Boolean {
        verifySearchFragmentState()
        movieSearchByName(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        verifySearchFragmentState()
        movieSearchByName(newText)
        return true
    }

    /**
     * On Search View Clear Click
     */
    override fun onClick(v: View?) {
        clearSearch()
    }

    // Menu Item Listener Methods
    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        mainTabLayout.visibility = View.GONE
        mainNameFilterFrame.visibility = View.VISIBLE
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        mainNameFilterFrame.visibility = View.GONE
        mainTabLayout.visibility = View.VISIBLE
        mainPagerView.visibility = View.VISIBLE
        clearSearch()
        return true
    }

    // Self Methods
    private fun clearSearch() {
        filteredListFragment!!.presenter.showEmptyMovieList()
        findViewById<EditText>(R.id.search_src_text).setText("")
    }

    private fun verifySearchFragmentState() {
        if (filteredListFragment == null) {
            filteredListFragment = MovieListFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mainNameFilterFrame, filteredListFragment!!)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun movieSearchByName(query: String?) {
        if (query!!.isNotBlank()) {
            filteredListFragment!!.presenter.requestMovieListByName(query)
            mainPagerView.visibility = View.GONE
        }
    }
}