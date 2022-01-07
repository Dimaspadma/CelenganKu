package com.udinus.celenganku.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.udinus.celenganku.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        const val USERNAME = "username"
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var username: String = "User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textUsername.text = username
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}