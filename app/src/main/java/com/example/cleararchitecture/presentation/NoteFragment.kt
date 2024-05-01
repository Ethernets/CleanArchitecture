package com.example.cleararchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cleararchitecture.R
import com.example.cleararchitecture.databinding.FragmentListBinding
import com.example.cleararchitecture.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    private lateinit var viewBinding: FragmentNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNoteBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.checkButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}