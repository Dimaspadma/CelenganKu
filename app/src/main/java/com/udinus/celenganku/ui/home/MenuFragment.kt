package com.udinus.celenganku.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udinus.celenganku.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toAbout.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToAboutFragment())
        }

        binding.toMap.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToMapsFragment())
        }

        binding.toCredit.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToCreditFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}