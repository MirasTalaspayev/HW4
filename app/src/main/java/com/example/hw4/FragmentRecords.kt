package com.example.hw4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw4.Records.userRecords
import com.example.hw4.databinding.FragmentGameBinding
import com.example.hw4.databinding.FragmentRecordsBinding

class FragmentRecords : Fragment() {

    private val args: FragmentRecordsArgs by navArgs()
    private var _binding: FragmentRecordsBinding? = null
    private val binding: FragmentRecordsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordsBinding.inflate(inflater, container, false)

        return _binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!userRecords.containsKey(args.winner)) {
            userRecords[args.winner] = ArrayList(3)
            userRecords[args.winner]!!.addAll(listOf(0, 0, 0))
            when (args.winOrlose) {
                1 -> {
                    userRecords[args.winner]?.set(0, 1)
                }
                0 -> {
                    userRecords[args.winner]?.set(1, 1)
                }
                else -> {
                    userRecords[args.winner]?.set(2, 1)
                }
            }
        }
        else {
            when (args.winOrlose) {
                1 -> {
                    userRecords[args.winner]?.set(0, (userRecords[args.winner]?.get(0)?.plus(1)!!))
                }
                0 -> {
                    userRecords[args.winner]?.set(0, (userRecords[args.winner]?.get(1)?.plus(1)!!))
                }
                else -> {
                    userRecords[args.winner]?.set(0, (userRecords[args.winner]?.get(2)?.plus(1)!!))
                }
            }
        }

        binding.run {
            userRecords.forEach {
                val textRecord = TextView(context)
                textRecord.text = "${it.key} - Wins: ${it.value[0]}, Loses: ${it.value[1]}, Draws: ${it.value[2]}"
                textRecord.textSize = 22F
                recordsPage.addView(textRecord)
            }

            btnHome.setOnClickListener() {
                findNavController().popBackStack(R.id.fragmentStart, true)
                findNavController().navigate(FragmentRecordsDirections.actionFragmentRecordsToFragmentStart())
            }
        }
    }
}