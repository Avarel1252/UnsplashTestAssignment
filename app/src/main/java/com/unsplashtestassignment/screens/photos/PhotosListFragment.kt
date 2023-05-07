package com.unsplashtestassignment.screens.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.unsplashtestassignment.data.room.DbPhotoEntity
import com.unsplashtestassignment.databinding.FragmentListBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PhotosListFragment : Fragment() {
    private var binding: FragmentListBinding? = null
    private val viewModel: PhotosListViewModel by viewModels()
    private val usersListAdapter: PhotosListAdapter? get() = views { rvPhotos.adapter as? PhotosListAdapter }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentListBinding.inflate(inflater).also { binding = it }.root

    private fun <T> views(block: FragmentListBinding.() -> T): T? = binding?.block()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views {
            rvPhotos.adapter = PhotosListAdapter(object : PhotosAdapterListener {
                override fun detailView(photo: DbPhotoEntity) {
                    findNavController().navigate(
                        PhotosListFragmentDirections.actionPhotosListFragmentToDetailViewFragment(
                            photo
                        )
                    )
                }
            })
            viewModel.photos.onEach(::renderPhotos).launchIn(lifecycleScope)
            btnUpload.setOnClickListener { viewModel.uploadNewPhotos(9) }
            btnClear.setOnClickListener { viewModel.deleteAll()}
        }
    }

    private fun renderPhotos(users: List<DbPhotoEntity>) {
        usersListAdapter?.submitList(users)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}