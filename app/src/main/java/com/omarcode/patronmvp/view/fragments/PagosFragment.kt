package com.omarcode.patronmvp.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.card.MaterialCardView
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.omarcode.patronmvp.R
import com.omarcode.patronmvp.helpers.log
import com.omarcode.patronmvp.model.pojos.ArtistItem
import com.omarcode.patronmvp.mvp.PagosMVP
import com.omarcode.patronmvp.presenter.PagosPresenter
import com.omarcode.patronmvp.view.adapters.PagosAdapter
import kotlinx.android.synthetic.main.bottom_sheet_pagos.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PagosFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PagosFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PagosFragment : Fragment(), View.OnClickListener, PagosMVP.View {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var presenter : PagosMVP.Presenter? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var cardView_pagos_realizados : MaterialCardView
    private lateinit var cardView_pagos_pendientes : MaterialCardView
    lateinit var manager : FragmentManager
    private var coordinatorLayout : CoordinatorLayout? = null
    lateinit var bottomSheet : View
    lateinit var recyclerview_pagos : RecyclerView
    lateinit var behavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.presenter = PagosPresenter(this)
        manager = fragmentManager!!
        presenter?.getList()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View           =  inflater.inflate(R.layout.fragment_pagos, container, false)
        cardView_pagos_realizados =  view.findViewById(R.id.cardView_pagos_realizados)
        cardView_pagos_pendientes =  view.findViewById(R.id.cardView_pagos_pendientes)

        coordinatorLayout = view.findViewById(R.id.coordinator_fragment_pagos)
        bottomSheet = view.findViewById(R.id.bottom_sheet)

        behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {

            }

            override fun onStateChanged(p0: View, p1: Int) {

            }

        })
        this.recyclerview_pagos   =  view.findViewById(R.id.recyclerview_pagos)
        this.recyclerview_pagos.layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
        this.recyclerview_pagos.setHasFixedSize(true)
        this.cardView_pagos_realizados.setOnClickListener(this)
        cardView_pagos_pendientes.setOnClickListener(this)
        return view
    }

    override fun onClick(p0: View?) {
        if(p0!!.id == R.id.cardView_pagos_realizados) {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else if(p0.id == R.id.cardView_pagos_pendientes) {
            val artistfragment = ArtistFragment.newInstance()
            artistfragment.show(manager, artistfragment.tag)
        }
    }

    override fun showList(list : List<ArtistItem>) {
        val adapter = PagosAdapter({
            log(it.name!!)
        })
        recyclerview_pagos.adapter = adapter
        adapter.setLista(list)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PagosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(args : Bundle?) =
                PagosFragment().apply {
                    if(args!=null) arguments = args
                }
    }
}
