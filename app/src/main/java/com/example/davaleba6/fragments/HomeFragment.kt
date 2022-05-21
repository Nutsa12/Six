package com.zura.mysuperapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zura.mysuperapp.R

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var button: Button
    private lateinit var text: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.button)
        text = view.findViewById(R.id.editText)

        button.setOnClickListener{
            var number: Int = text.text.toString().toInt()
            var action = HomeFragmentDirections.actionHomeFragmentToDashboardFragment(number)
            findNavController().navigate(action)
        }
    }

}