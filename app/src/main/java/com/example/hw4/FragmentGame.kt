package com.example.hw4

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw4.databinding.FragmentGameBinding


class FragmentGame : Fragment() {

    private val args: FragmentGameArgs by navArgs()

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding get() = _binding!!

    private var turn = 0

    private val userNames = ArrayList<String>(2)
    private val drawables = ArrayList<Int>(2)
    private val images = ArrayList<ArrayList<ImageView>>()
    private var winOrlose = 0;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userNames.addAll(listOf(args.user1, args.user2))
        drawables.addAll(listOf(R.drawable.ic_x, R.drawable.ic_o))

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        _binding?.lifecycleOwner = viewLifecycleOwner
        _binding?.userTurn = userNames[turn]

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageRow0 = ArrayList<ImageView>()
        imageRow0.addAll(listOf(binding.imgView00, binding.imgView01, binding.imgView02))
        val imageRow1 = ArrayList<ImageView>()
        imageRow1.addAll(listOf(binding.imgView10, binding.imgView11, binding.imgView12))
        var imageRow2 = ArrayList<ImageView>()
        imageRow2.addAll(listOf(binding.imgView20, binding.imgView21, binding.imgView22))
        images.addAll(listOf(imageRow0, imageRow1, imageRow2))

        images[0][0].onClick()
        images[0][1].onClick()
        images[0][2].onClick()
        images[1][0].onClick()
        images[1][1].onClick()
        images[1][2].onClick()
        images[2][0].onClick()
        images[2][1].onClick()
        images[2][2].onClick()

        binding.btnContinue.setOnClickListener() {
            findNavController().navigate(FragmentGameDirections.actionFragmentGameToFragmentRecords(userNames[(turn - 1) % 2], winOrlose))
        }
    }
    private fun Winner(images: ArrayList<ArrayList<ImageView>>): Int {
        for (i in 0..2) {
            if (images[i][0].tag != null && images[i][1].tag != null && images[i][2].tag != null
                && images[i][0].tag == images[i][1].tag && images[i][1].tag == images[i][2].tag) {
                images[i][0].setBackgroundColor(Color.rgb(255, 0, 0))
                images[i][1].setBackgroundColor(Color.rgb(255, 0, 0))
                images[i][2].setBackgroundColor(Color.rgb(255, 0, 0))
                return 1
            }
            else if (images[0][i].tag != null && images[1][i].tag != null && images[2][i].tag != null
                && images[0][i].tag == images[1][i].tag && images[1][i].tag == images[2][i].tag) {
                images[0][i].setBackgroundColor(Color.rgb(255, 0, 0))
                images[1][i].setBackgroundColor(Color.rgb(255, 0, 0))
                images[2][i].setBackgroundColor(Color.rgb(255, 0, 0))
                return 1
            }
        }
        if (images[0][0].tag != null && images[0][1].tag != null && images[0][2].tag != null
            && images[0][0].tag == images[1][1].tag && images[1][1].tag == images[2][2].tag) {
            images[0][0].setBackgroundColor(Color.rgb(255, 0, 0))
            images[1][1].setBackgroundColor(Color.rgb(255, 0, 0))
            images[2][2].setBackgroundColor(Color.rgb(255, 0, 0))
            return 1
        }
        else if (images[0][2].tag != null && images[1][1].tag != null && images[2][0].tag != null
                && images[0][2].tag == images[1][1].tag && images[1][1].tag == images[2][0].tag) {
            images[0][2].setBackgroundColor(Color.rgb(255, 0, 0))
            images[1][1].setBackgroundColor(Color.rgb(255, 0, 0))
            images[2][0].setBackgroundColor(Color.rgb(255, 0, 0))
            return 1
        }
        return 0
    }
    private fun ImageView.onClick() {
        this.setOnClickListener() {
            this.setBackgroundColor(Color.rgb(255, 255, 255))
            this.setImageResource(drawables[turn % 2])
            this.tag = userNames[turn % 2]
            turn++
            binding.userTurn = userNames[turn % 2]
            if (turn >= 5) {
                winOrlose = Winner(images)
                if (winOrlose == 1) {
                    binding.txtUserTurn.text = "Winner is "
                    binding.txtUserTurn.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    binding.userTurn = userNames[(turn - 1) % 2]
                    binding.btnContinue.visibility = View.VISIBLE
                }
            }
            if (turn == 9) {
                if (winOrlose != 1) {
                    winOrlose = -1; // draw
                    binding.txtUserTurn.text = "Draw"
                    binding.txtUserTurn.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    binding.userTurn = ""
                    binding.btnContinue.visibility = View.VISIBLE
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}