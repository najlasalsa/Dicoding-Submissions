package com.example.submission2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailUserAdapter(
    frag: FragmentActivity,
    private val fragments: MutableList<Fragment>
) :
    FragmentStateAdapter(frag) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}