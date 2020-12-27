package com.example.test_6

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class BlankFragment1 : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("BlankFragment1","onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BlankFragment1","onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("BlankFragment1","onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("BlankFragment1","onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("BlankFragment1","onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("BlankFragment1","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("BlankFragment1","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("BlankFragment1","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("BlankFragment1","onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("BlankFragment1","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BlankFragment1","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("BlankFragment1","onDetach")
    }


}