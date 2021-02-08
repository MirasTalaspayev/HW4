package com.example.hw4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hw4.databinding.FragmentUsernamesBinding
class FragmentUsernames : Fragment(R.layout.fragment_usernames) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUsernamesBinding.bind(view)

        binding.run {
            btnPlayGame.setOnClickListener() {
                val userName1 = username1.text.toString()
                val userName2 = username2.text.toString()
                if (userName1.isEmpty() || userName2.isEmpty()) {
                    Toast.makeText(view.context, "Usernames should NOT be empty.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else if (userName1 == userName2) {
                    Toast.makeText(view.context, "Usernames should NOT be same.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                findNavController().navigate(FragmentUsernamesDirections.actionFragmentUsernamesToFragmentGame(
                    userName1, userName2))
            }
        }
    }
}