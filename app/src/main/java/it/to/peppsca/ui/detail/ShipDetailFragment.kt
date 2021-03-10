package it.to.peppsca.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import it.to.peppsca.databinding.FragmentShipDetailsBinding
import it.to.peppsca.ui.base.BaseFragment

/**
 * A [ShipDetailFragment] that displays a list of pirates ships
 */
@AndroidEntryPoint
class ShipDetailFragment : BaseFragment<FragmentShipDetailsBinding>() {

    private val viewModel: ShipDetailViewModel by viewModels()

    private val args: ShipDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setBinding(FragmentShipDetailsBinding.inflate(inflater, container, false))
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.setUpShip(args.pirateShipModel)

        binding.fabDynamic.setOnClickListener {
            showSnackBar(args.pirateShipModel.greeting)
        }

        return binding.root
    }

    private fun showSnackBar(greeting: String) {
        view?.let {
            Snackbar.make(it, greeting, Snackbar.LENGTH_LONG).show()
        }

    }
}