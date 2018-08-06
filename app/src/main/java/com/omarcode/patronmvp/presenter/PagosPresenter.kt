package com.omarcode.patronmvp.presenter

import com.omarcode.patronmvp.model.interactor.PagosInteractor
import com.omarcode.patronmvp.model.pojos.ArtistItem
import com.omarcode.patronmvp.mvp.PagosMVP

class PagosPresenter(view : PagosMVP.View) : PagosMVP.Presenter {

    private var interactor : PagosInteractor? = null
    private var view : PagosMVP.View? = null

    init {
        this.view = view
        this.interactor = PagosInteractor(this)
    }

    override fun showList(list : List<ArtistItem>) {
        this.view?.showList(list)
    }

    override fun getList() {
        this.interactor?.getList()
    }
}