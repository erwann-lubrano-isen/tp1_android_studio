package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentPictureBinding
import com.squareup.picasso.Picasso


class PictureFragment : Fragment() {
    lateinit var binding: FragmentPictureBinding
    private var picture : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            picture = it.getString(ARG_PARAM1)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureBinding.inflate(layoutInflater)
        if(!picture.isNullOrEmpty())
                Picasso.get().load(picture).into(binding.fragmentPictureImageView)
        return binding.root
    }

    companion object {
        val ARG_PARAM1 = "ARG_PARAM1"

        fun newInstance(picture: String) =
            PictureFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, picture)
                }
            }
    }

}