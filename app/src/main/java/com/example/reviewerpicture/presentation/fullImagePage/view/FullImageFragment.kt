package com.example.reviewerpicture.presentation.fullImagePage.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.reviewerpicture.databinding.FragmentDetailsBinding
import com.example.reviewerpicture.presentation.di.Injector
import com.example.reviewerpicture.presentation.fullImagePage.viewModel.FullImageVMF
import com.example.reviewerpicture.presentation.fullImagePage.viewModel.FullImageViewModel
import com.example.reviewerpicture.utils.emptyString
import com.example.reviewerpicture.utils.getBitmapImage
import javax.inject.Inject


class FullImageFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    @Inject
    lateinit var vmf: FullImageVMF

    lateinit var viewModel: FullImageViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as Injector).createFullImageSubComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, vmf).get(FullImageViewModel::class.java)
        arguments?.let {
            viewModel.imageUrl = it.getString(ARG_IMAGE_INPUT)?: emptyString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi(){
        if(viewModel.imageUrl.isNotEmpty())
            binding.ivImage.setImageBitmap(getBitmapImage(viewModel.imageUrl))
    }

    companion object {
        const val ARG_IMAGE_INPUT = "image_input"
    }
}