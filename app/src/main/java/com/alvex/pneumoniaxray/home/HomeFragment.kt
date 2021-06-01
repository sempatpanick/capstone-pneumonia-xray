package com.alvex.pneumoniaxray.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alvex.pneumoniaxray.core.data.UserRepository
import com.alvex.pneumoniaxray.core.manager.SessionManager
import com.alvex.pneumoniaxray.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var userRepository: UserRepository

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val sesi = SessionManager(requireContext())
            userRepository = UserRepository.getInstance(sesi)

            binding.tvDoctorName.text = userRepository.getUser().nama
        }
    }
}