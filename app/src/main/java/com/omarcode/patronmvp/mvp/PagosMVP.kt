package com.omarcode.patronmvp.mvp

import com.omarcode.patronmvp.model.pojos.ArtistItem

interface PagosMVP {

    interface View {
        fun showList(list : List<ArtistItem>)
    }
    interface Presenter {
        fun showList(list : List<ArtistItem>)
        fun getList()
    }
    interface Interactor {
        fun getList()
        fun showList(list : List<ArtistItem>)
    }
    interface Repository {
        fun getList()
    }
}