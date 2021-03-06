package com.sugarmaniac.testapplication.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sugarmaniac.testapplication.R
import com.sugarmaniac.testapplication.model.BindableRecyclerViewAdapter
import com.sugarmaniac.testapplication.model.Device
import com.sugarmaniac.testapplication.viewModel.BaseViewModel


class ListFragment : Fragment() , BindableRecyclerViewAdapter.ItemClickListener {

    private val sharedViewModel : BaseViewModel by activityViewModels()
    private lateinit var recyclerViewAdapter: BindableRecyclerViewAdapter

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

        initRecyclerView(view)
        initObserver()
//        sharedViewModel.fetchData()

        return view
    }

    private fun initObserver() {
        sharedViewModel.deviceData.observe(viewLifecycleOwner, Observer { deviceList ->
            recyclerViewAdapter.updateItems(deviceList)
        })
    }

    private fun initRecyclerView(view: View) {
        var recyclerView = view.findViewById<RecyclerView>(R.id.device_rv)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewAdapter = BindableRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.setOnClickListener(this)

        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(requireContext().getDrawable(R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecorator)
    }

    private fun showDeleteDialog(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.delete_item))
        builder.setTitle(getString(R.string.DELETE))
        builder.setPositiveButton("OK"){ dialog, which -> deleteItem(position) }
        builder.setNegativeButton("CANCEL"){ dialog, which -> }
        builder.show()
    }

    private fun deleteItem(position: Int) {
        sharedViewModel.deleteDevice(position)
    }

    //Click Listener
    override fun onItemClick(device: Device) {
        val bundle = Bundle()
        bundle.putParcelable("device", device)
        findNavController().navigate(R.id.action_listFragment_to_deviceFragment, bundle)
    }

    override fun onDeleteClick(position: Int) {
        showDeleteDialog(position)
    }

    override fun onEditClick(device: Device) {
        val bundle = Bundle()
        bundle.putParcelable("device", device)
        findNavController().navigate(R.id.action_listFragment_to_editFragment, bundle)
    }

}