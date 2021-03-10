package it.to.peppsca.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import it.to.peppsca.databinding.FragmentShipDetailsBinding

/**
 * A [ShipDetailFragment] that displays a list of pirates ships
 */
@AndroidEntryPoint
class ShipDetailFragment : Fragment() {

    private val viewModel: ShipDetailViewModel by viewModels()

    private var _binding: FragmentShipDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShipDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.setUpShip()
        return binding.root
    }
}