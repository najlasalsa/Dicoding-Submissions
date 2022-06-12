package com.example.submission2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.submission2.databinding.FragmentFollBinding

class FollFragment : Fragment() {

    private val binding: FragmentFollBinding by viewBinding()
    private lateinit var viewModel: MainViewModel
    private var pos = 0
    var foll = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFollBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        if (pos == USER_FOLLOWERS) {
            viewModel.setUserFollowers(foll)
        } else {
            viewModel.setUserFollowing(foll)
        }
        viewModel.isLoad.observe(viewLifecycleOwner) {
            showLoad(it)
        }
        viewModel.follow.observe(viewLifecycleOwner) { follow ->
            binding.rvFrag.apply {
                adapter = UserAdapter(follow)
                layoutManager = LinearLayoutManager(requireActivity())
                setHasFixedSize(true)
            }
        }
    }

    private fun showLoad(isLoad: Boolean) {
        binding.progressBar.visibility = if (isLoad) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        const val USER_FOLLOWERS = 0
        const val USER_FOLLOWING = 1

        fun newInstance(pos: Int): FollFragment =
            FollFragment().apply {
                this.pos = pos
            }
    }
}