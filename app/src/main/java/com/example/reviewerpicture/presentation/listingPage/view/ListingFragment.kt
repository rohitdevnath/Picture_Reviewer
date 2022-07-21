package com.example.reviewerpicture.presentation.listingPage.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reviewerpicture.presentation.fullImagePage.view.FullImageFragment
import com.example.reviewerpicture.R
import com.example.reviewerpicture.data.model.networkModel.AllDataNetworkModel
import com.example.reviewerpicture.databinding.FragmentHomeBinding
import com.example.reviewerpicture.presentation.di.Injector
import com.example.reviewerpicture.presentation.listingPage.viewModel.ListingVMF
import com.example.reviewerpicture.presentation.listingPage.viewModel.ListingViewModel
import javax.inject.Inject


class ListingFragment : Fragment() {

    private val rvAdapter: ListingAdapter by lazy { ListingAdapter() }

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var vmf: ListingVMF

    private lateinit var viewModel: ListingViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as Injector).createListingSubComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, vmf).get(ListingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        getData()
    }

    private fun setUpUi(){

        binding.rvContent.layoutManager = LinearLayoutManager(context)
        binding.rvContent.adapter = rvAdapter

        binding.btnSubmit.setOnClickListener {
            val input: String = "dsdsvv"
            val bundle = bundleOf(FullImageFragment.ARG_USER_INPUT to input)
            it.findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        }
    }

    private fun getData(){
        val list: List<AllDataNetworkModel> = viewModel.getAllData()
        rvAdapter.setData(list)
    }
}