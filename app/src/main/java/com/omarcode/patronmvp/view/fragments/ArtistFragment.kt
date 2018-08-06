package com.omarcode.patronmvp.view.fragments

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.omarcode.patronmvp.R
import com.omarcode.patronmvp.helpers.log
import com.omarcode.patronmvp.model.pojos.ArtistItem
import com.omarcode.patronmvp.mvp.PagosMVP
import com.omarcode.patronmvp.presenter.PagosPresenter
import com.omarcode.patronmvp.view.adapters.PagosAdapter
import android.support.annotation.NonNull



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ArtistFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ArtistFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ArtistFragment : BottomSheetDialogFragment(), PagosMVP.View {

    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }

        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var presenter: PagosPresenter? = null
    lateinit var adapter: PagosAdapter
    private var listener: OnFragmentInteractionListener? = null
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.presenter = PagosPresenter(this)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun setupDialog(dialog: Dialog?, style: Int) {

        val view = View.inflate(context, R.layout.bottom_sheet_pagos, null)
        this.recyclerView = view!!.findViewById(R.id.recyclerview_pagos)
        this.recyclerView.layoutManager = GridLayoutManager(context, 2)
        this.recyclerView.setHasFixedSize(true)
        this.presenter?.getList()
        dialog?.setContentView(view)

        val params: CoordinatorLayout.LayoutParams = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior: CoordinatorLayout.Behavior<View> = params.behavior!!
        if (behavior is BottomSheetBehavior) {
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun showList(list: List<ArtistItem>) {
        adapter = PagosAdapter {
            log(it.name!!)
        }
        recyclerView.adapter = adapter
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
         * @return A new instance of fragment ArtistFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                ArtistFragment().apply {
                    arguments = Bundle().apply {

                    }
                }

    }
}
