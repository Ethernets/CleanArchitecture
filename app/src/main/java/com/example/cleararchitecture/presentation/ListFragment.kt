package com.example.cleararchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleararchitecture.databinding.FragmentListBinding
import com.example.cleararchitecture.framework.ListViewModel
import com.example.cleararchitecture.framework.NoteListViewModelFactory
import com.example.cleararchitecture.framework.NoteViewModel
import com.example.cleararchitecture.framework.NoteViewModelFactory

class ListFragment : Fragment(), ListAction {
    private lateinit var viewBinding: FragmentListBinding
    private val noteListAdapter = NoteListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ListViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = NoteListViewModelFactory(this.requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)

        viewBinding.notesListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }
        viewBinding.addNoteButton.setOnClickListener {
            goToNoteDetails()
        }
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNote()
    }

    private fun observeViewModel() {
        viewModel.note.observe(viewLifecycleOwner, Observer {
            viewBinding.loadingBar.visibility = View.GONE
            viewBinding.notesListView.visibility = View.VISIBLE
            noteListAdapter.updateNotes(it.sortedByDescending { it.updateTime })

        })
    }

    private fun goToNoteDetails(id: Long = 0L){
        val action = ListFragmentDirections.actionGoToNote(id)
        findNavController().navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}