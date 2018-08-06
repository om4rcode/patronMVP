package com.omarcode.patronmvp.model.interactor

import com.omarcode.patronmvp.model.pojos.ArtistItem
import com.omarcode.patronmvp.model.services.repository.PagosRepository
import com.omarcode.patronmvp.mvp.PagosMVP
import com.omarcode.patronmvp.presenter.PagosPresenter

class PagosInteractor(presenter : PagosPresenter) : PagosMVP.Interactor {

    var presenter  : PagosMVP.Presenter? = null
    var repository : PagosMVP.Repository? = null
    init {
        this.presenter = presenter
        this.repository = PagosRepository(this)
    }

    override fun getList() {
        this.repository?.getList()
    }

    override fun showList(list : List<ArtistItem>) {
        this.presenter?.showList(list)
    }
}