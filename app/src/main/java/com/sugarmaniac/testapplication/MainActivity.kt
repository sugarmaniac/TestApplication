package com.sugarmaniac.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sugarmaniac.testapplication.viewModel.BaseViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel : BaseViewModel by viewModels()
        viewModel.fetchData()
    }

    override fun onBackPressed() {
        findNavController(R.id.fragmentContainerView).navigateUp()
    }


}