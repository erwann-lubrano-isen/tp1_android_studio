package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PictureAdapter(val images : List<String>, activity : AppCompatActivity)
    :   FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return images.count()
    }

    override fun createFragment(position: Int): Fragment {
        return PictureFragment.newInstance(images[position])
    }

}