package com.sugarmaniac.testapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.sugarmaniac.testapplication.R
import com.sugarmaniac.testapplication.viewModel.BaseViewModel

class EditFragment : Fragment() {

    private val sharedViewModel : BaseViewModel by activityViewModels()
    private var deviceSN : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            deviceSN = requireArguments().getInt("deviceSN")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    private fun changeDeviceTitle(title: String){
        sharedViewModel.changeDeviceTitle(title, deviceSN)
    }
}