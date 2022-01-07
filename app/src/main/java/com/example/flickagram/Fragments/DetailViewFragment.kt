package com.example.flickagram.Fragments

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.flickagram.databinding.FragmentDetailViewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailViewFragment : Fragment() {


    lateinit var binding: FragmentDetailViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailViewBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = requireArguments().get("url")
        val name = requireArguments().get("name")


        Glide.with(requireContext()).load(url).into(binding.ivDetailedImage)
        binding.tvImageName.text = name.toString()
        binding.tvShareImage.setOnClickListener {


            val bitmapDrawable = binding.ivDetailedImage.drawable as BitmapDrawable


            val bitmap = bitmapDrawable.bitmap

            d("mytag", "bitmap :  $bitmap")
            val resolver = activity?.contentResolver
            d("mytag", " resolver : $resolver")

            val path = MediaStore.Images.Media.insertImage(resolver, bitmap, "Share it to", "none")
            d("mytag", " path : $path")
            val bitmapUri = Uri.parse(path)

            val share = Intent(Intent.ACTION_SEND).apply {

                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, bitmapUri)
            }

            startActivity(Intent.createChooser(share, "Share via"))

        }
        binding.tvShareLink.setOnClickListener {
            val sharelink = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, url.toString())
                type = "text/plain"
            }
            startActivity(sharelink)
        }

    }
}