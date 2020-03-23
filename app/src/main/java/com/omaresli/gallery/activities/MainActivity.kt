package com.omaresli.gallery.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.widget.SearchView
import com.omaresli.gallery.BR
import com.omaresli.gallery.R
import com.omaresli.gallery.adapters.CatalogAdapter
import com.omaresli.gallery.databinding.ActivityMainBinding
import com.omaresli.gallery.interactors.CatalogInteractor
import com.omaresli.gallery.repositories.CatalogRepository
import com.omaresli.gallery.repositories.CatalogRepositoryImplementation
import com.omaresli.gallery.routers.AndroidCatalogRouter


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var interactor: CatalogInteractor
    val repository: CatalogRepository = CatalogRepositoryImplementation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val catalogAdapter = CatalogAdapter(BR.viewModel)
        interactor = CatalogInteractor(repository, AndroidCatalogRouter(catalogAdapter, this))
        setupRecyclerView(catalogAdapter)

        handleIntent(intent)
    }

    private fun setupRecyclerView(catalogAdapter: CatalogAdapter) {
        binding.viewRecyclerCatalog.adapter = catalogAdapter
        val gridLayoutManager = GridLayoutManager(this, 3)
        binding.viewRecyclerCatalog.layoutManager = gridLayoutManager
        binding.viewRecyclerCatalog.addOnScrollListener(EndlessListener(gridLayoutManager) { onLoadMore() })
    }

    override fun onNewIntent(intent: Intent) {
        setIntent(intent)
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.catalog_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        menu?.let {
            val searchView: SearchView = it.findItem(R.id.search).actionView as SearchView
            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            TODO()
        }
    }

    private fun onLoadMore() {
        interactor.onLoadNextPage(this)
    }

    class EndlessListener(private val layoutManager: GridLayoutManager, private val loadMore: () -> Unit) : RecyclerView.OnScrollListener() {
        companion object {
            private const val MAX_ITEMS_BEFORE_SCROLL: Int = 5
        }

        private var scrolling = false

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            scrolling = true
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> if (scrolling) {
                    scrolling = false

                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                    if (totalItemCount <= (lastVisibleItem + MAX_ITEMS_BEFORE_SCROLL)) {
                        scrolling = false
                        loadMore.invoke()
                    }
                }
            }
        }

    }
}
