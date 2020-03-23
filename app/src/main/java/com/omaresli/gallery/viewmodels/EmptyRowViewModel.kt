package com.omaresli.gallery.viewmodels

import com.omaresli.gallery.R

class EmptyRowViewModel : RowViewModel {
    companion object {
        const val ID = "EMPTY_ROW_ID"
    }
    override val id: String = ID
    override val resourceLayout: Int = R.layout.layout_empty_row
}