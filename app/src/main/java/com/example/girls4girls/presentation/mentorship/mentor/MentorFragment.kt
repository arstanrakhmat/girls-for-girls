package com.example.girls4girls.presentation.mentorship.mentor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.girls4girls.databinding.FragmentMentorBinding

class MentorFragment : Fragment() {

    private lateinit var binding: FragmentMentorBinding
    private val args by navArgs<MentorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentMentorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        initAdapter()

    }

    private fun initAdapter() {
        val mentorVideoAdapter = MentorVideoAdapter()
        binding.mentorVideosList.adapter = mentorVideoAdapter
        mentorVideoAdapter.submitList(args.mentor.videos)
        mentorVideoAdapter.onVideoClickListener ={ videoBlog ->
            val action = MentorFragmentDirections.
                    actionMentorFragmentToVideoblogFragment2(videoBlog)
            findNavController().navigate(action)
        }
    }

    private fun initViews() {
        Glide
            .with(binding.root)
            .load(args.mentor.image)
            .into(binding.imageView)

        binding.mentorName.text = args.mentor.name
    }
}