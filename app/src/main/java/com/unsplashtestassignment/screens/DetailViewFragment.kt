package com.unsplashtestassignment.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.unsplashtestassignment.databinding.FragmentDetailViewBinding
import com.unsplashtestassignment.utils.extensions.loadPhoto

class DetailViewFragment : Fragment() {
    private var binding: FragmentDetailViewBinding? = null
    private val args by navArgs<DetailViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailViewBinding.inflate(inflater).also { binding = it }.root

    private fun <T> views(block: FragmentDetailViewBinding.() -> T): T? = binding?.block()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views {
            btnBack.setOnClickListener { findNavController().navigateUp() }
            tvName.text = "ID: ${args.photo.photo_id}"
            tvAuthor.text = "Author: ${args.photo.name}"
            ivPhoto.loadPhoto(args.photo.raw)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}