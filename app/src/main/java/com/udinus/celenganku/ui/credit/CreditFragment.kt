package com.udinus.celenganku.ui.credit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udinus.celenganku.databinding.FragmentCreditBinding

class CreditFragment : Fragment() {

    private var _binding: FragmentCreditBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreditBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toAnggota1.setOnClickListener {
            findNavController().navigate(CreditFragmentDirections.actionCreditFragmentToCreditPadmaFragment())
        }
        binding.toAnggota2.setOnClickListener {
            findNavController().navigate(CreditFragmentDirections.actionCreditFragmentToCreditZarekFragment())
        }
        binding.toAnggota3.setOnClickListener {
            findNavController().navigate(CreditFragmentDirections.actionCreditFragmentToCreditDenniFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}