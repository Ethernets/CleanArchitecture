package com.example.cleararchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleararchitecture.R
import com.example.cleararchitecture.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var viewBinding: FragmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
}