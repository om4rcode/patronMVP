package com.omarcode.patronmvp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.omarcode.patronmvp.R
import com.omarcode.patronmvp.helpers.inflate
import com.omarcode.patronmvp.model.pojos.ArtistItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pagos.view.*

class PagosAdapter(val listener : (ArtistItem) -> Unit) : RecyclerView.Adapter<PagosAdapter.ViewHolder>() {

    private var lista : List<ArtistItem> = ArrayList<ArtistItem>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
       return ViewHolder(p0.inflate(R.layout.item_pagos))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(lista[p1], listener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item : ArtistItem, listener : (ArtistItem) -> Unit) = with(itemView) {
            var urlImg = item.image?.get(0)?.text
            this.tvNomArtist.text = item.name
            Picasso.get().load(urlImg).into(imgArtist)
            this.setOnClickListener {
                listener(item)
            }
        }
    }

     fun setLista(items : List<ArtistItem>) {
       this.lista = items
    }
}