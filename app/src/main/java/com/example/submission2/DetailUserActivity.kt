package com.example.submission2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission2.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val username = intent.getStringExtra(DETAIL_USER)
        viewModel.setUserDetail(username)
        viewModel.users.observe(this) { users ->
            binding.apply {
                Glide.with(this@DetailUserActivity)
                    .load(users.avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.github)
                    .error(R.drawable.github)
                    .into(detailUserProfile)
                detailUserName.text = users.name
                detailUserUsername.text = users.username
                detailUserFollowers.text = users.followers.toString()
                detailUserFollowing.text = users.following.toString()
                detailUserCompany.text = users.company
                detailUserLocation.text = users.location
                detailUserRepos.text = users.publicRepos.toString()

                val fragments = mutableListOf<Fragment>(
                    FollFragment.newInstance(FollFragment.USER_FOLLOWERS).apply {
                        foll = users.username
                    },
                    FollFragment.newInstance(FollFragment.USER_FOLLOWING).apply {
                        foll = users.username
                    }
                )
                val title = mutableListOf(
                    getString(R.string.detail_followers),
                    getString(R.string.detail_following)
                )
                val detailUserAdapter = DetailUserAdapter(this@DetailUserActivity, fragments)
                pageViews.adapter = detailUserAdapter

                TabLayoutMediator(tabDetail, pageViews) { tab, position ->
                    tab.text = title[position]
                }.attach()
            }
        }
    }

    companion object {
        const val DETAIL_USER = "detail_user"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}