package com.sugarmaniac.testapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sugarmaniac.testapplication.R
import com.sugarmaniac.testapplication.databinding.FragmentDeviceBinding
import com.sugarmaniac.testapplication.model.Device

class DeviceFragment : Fragment() {

    private var device : Device? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            device = arguments?.getParcelable("device")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBind = DataBindingUtil.inflate<FragmentDeviceBinding>(inflater, R.layout.fragment_device, container, false)
        dataBind.device = device

        return dataBind.root
    }
}