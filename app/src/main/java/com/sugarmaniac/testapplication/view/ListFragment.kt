package com.sugarmaniac.testapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sugarmaniac.testapplication.R
import com.sugarmaniac.testapplication.model.DevicesModel
import com.sugarmaniac.testapplication.viewModel.BaseViewModel

class ListFragment : Fragment() {

    private val sharedViewModel : BaseViewModel by activityViewModels()
    private lateinit var recyclerViewAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_list, container, false)

        sharedViewModel.fetchData()
        initObserver()
        initRecyclerView()

        return view
    }

    private fun initObserver() {
        sharedViewModel.deviceData.observe(viewLifecycleOwner, Observer { deviceList ->
            setItems(deviceList)
        })
    }

    private fun setItems(deviceList: DevicesModel?) {

    }

    private fun initRecyclerView() {

    }

    fun navigateToDevice(){
        findNavController().navigate(R.id.action_listFragment_to_deviceFragment)
    }

    fun navigateToEdit(){
        findNavController().navigate(R.id.action_listFragment_to_editFragment)
    }
}