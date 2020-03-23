package com.omaresli.gallery.viewmodels

import com.omaresli.gallery.R

class LoadingRowViewModel : RowViewModel {
    companion object {
        const val ID = "LOADING_ROW_ID"
    }

    override val id: String = ID
    override val resourceLayout: Int = R.layout.layout_loading_row
}