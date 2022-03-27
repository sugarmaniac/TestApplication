package com.sugarmaniac.testapplication.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.sugarmaniac.testapplication.R
import com.sugarmaniac.testapplication.model.Device
import com.sugarmaniac.testapplication.viewModel.BaseViewModel
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.sugarmaniac.testapplication.databinding.FragmentEditBinding


class EditFragment : Fragment() {

    private val sharedViewModel : BaseViewModel by activityViewModels()
    private var device : Device? = null
    private lateinit var editText : EditText

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

        val fragmentEditBinding = DataBindingUtil.inflate<FragmentEditBinding>(inflater, R.layout.fragment_edit, container, false)
        val view = fragmentEditBinding.root
        fragmentEditBinding.device = device

        editText = view.findViewById<EditText>(R.id.device_title)
        editText.addTextChangedListener { text -> changeDeviceTitle(text.toString()) }
        editText.requestFocus()

        return view
    }

    private fun changeDeviceTitle(title: String){
        sharedViewModel.changeDeviceTitle(title, device?.PK_Device)
        showKeyboard()
    }

    private fun showKeyboard(){
        val imm: InputMethodManager? = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }
}