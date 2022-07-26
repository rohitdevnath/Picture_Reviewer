package com.example.reviewerpicture.presentation.listingPage.view

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reviewerpicture.R
import com.example.reviewerpicture.data.model.uiModel.CommentDataUiModel
import com.example.reviewerpicture.data.model.uiModel.ImageDataUiModel
import com.example.reviewerpicture.data.model.uiModel.SingleOptionUiModel
import com.example.reviewerpicture.databinding.FragmentHomeBinding
import com.example.reviewerpicture.presentation.di.Injector
import com.example.reviewerpicture.presentation.fullImagePage.view.FullImageFragment
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_AddImage
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_CommentChanged
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_CommentToggled
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_EnlargeImage
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_OptionClicked
import com.example.reviewerpicture.presentation.interactions.Interaction.Companion.INTERACTION_RemoveImage
import com.example.reviewerpicture.presentation.listingPage.viewModel.ListingVMF
import com.example.reviewerpicture.presentation.listingPage.viewModel.ListingViewModel
import com.example.reviewerpicture.utils.addTo
import com.example.reviewerpicture.utils.networkUtils.Outcome
import com.example.reviewerpicture.utils.performOnBackOutOnMain
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ListingFragment : Fragment() {

    private lateinit var currentPhotoPath: String

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }


    private val rvAdapter: ListingAdapter by lazy { ListingAdapter() }

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var vmf: ListingVMF

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    private lateinit var viewModel: ListingViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as Injector).createListingSubComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this, vmf).get(ListingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpListeners()
        getData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_submit, menu)

        menu.findItem(R.id.menuSubmit).actionView.findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            viewModel.submitResponse()
        }
    }
    private fun setUpUi(){

        binding.rvContent.layoutManager = LinearLayoutManager(context)
        binding.rvContent.adapter = rvAdapter

       rvAdapter.clickInteractions.performOnBackOutOnMain().subscribe {
           when (it.type) {
               INTERACTION_OptionClicked -> {
                   val uiModel = it.entity as SingleOptionUiModel
                   viewModel.updateOption(uiModel)
               }
               INTERACTION_CommentToggled-> {
                   val uiModel = it.entity as CommentDataUiModel
                   viewModel.updateCommentToggle(uiModel)
               }

               INTERACTION_CommentChanged-> {
                   val uiModel = it.entity as CommentDataUiModel
                   viewModel.updateCommentText(uiModel)
               }

               INTERACTION_AddImage -> {
                   val uiModel = it.entity as ImageDataUiModel
                   viewModel.currentImageDataUiModel = uiModel
                   dispatchTakePictureIntent()
               }

               INTERACTION_EnlargeImage -> {
                   val uiModel = it.entity as ImageDataUiModel


                   val bundle = bundleOf(FullImageFragment.ARG_IMAGE_INPUT to uiModel.imagePath)

                   binding.root.findNavController().let { navCon ->
                       navCon.currentDestination?.let {
                           if(it.id == R.id.homeFragment)
                               navCon.navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
                       }
                   }
               }

               INTERACTION_RemoveImage -> {
                   val uiModel = it.entity as ImageDataUiModel
                   viewModel.removeImage(uiModel)
               }
           }
        }.addTo(compositeDisposable)
    }

    private fun getData(forceRemote:Boolean = false){
        viewModel.getAllData(forceRemote)
    }

    private fun setUpListeners(){
        viewModel.allData.observe( this.viewLifecycleOwner, { outcome ->

            when(outcome){
                is Outcome.Progress -> {

                }
                is Outcome.Success -> {
                    rvAdapter.setData(outcome.data, outcome.silent.not())
                }
                is Outcome.Failure -> {

                }
            }

        })

        viewModel.submitData.observe(this.viewLifecycleOwner,{ outcome->
            when(outcome){
                is Outcome.Progress -> {

                }
                is Outcome.Success -> {
                    Toast.makeText(context,outcome.data,Toast.LENGTH_SHORT).show()
                    getData(forceRemote = true)
                }
                is Outcome.Failure -> {
                    Toast.makeText(context,outcome.e.message.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        })
    }



    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        requireContext().getString(R.string.file_provider_authority),
                        it
                    )
                    viewModel.currentImageUri = photoURI
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            viewModel.updateImagePath(currentPhotoPath)
        }
    }

    companion object{
        private const val REQUEST_IMAGE_CAPTURE = 1001
    }
}