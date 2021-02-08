package com.example.hw4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hw4.databinding.FragmentStartBinding


class FragmentStart : Fragment(R.layout.fragment_start) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStartBinding.bind(view)
        binding.run {
            binding.btnPlay.setOnClickListener() {
                findNavController().navigate(R.id.action_fragmentStart_to_fragmentUsernames)
            }
            binding.btnRecords.setOnClickListener() {
                findNavController().navigate(R.id.action_fragmentStart_to_fragmentRecords)
            }
        }
    }
}