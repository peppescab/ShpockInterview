package it.to.peppsca.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import it.to.peppsca.R
import it.to.peppsca.databinding.FragmentShipslistBinding
import it.to.peppsca.ui.base.BaseFragment
import it.to.peppsca.ui.list.adapter.PirateShipsAdapter

/**
 * A [ShipsListFragment] that displays a list of pirates ships
 */
@AndroidEntryPoint
class ShipsListFragment : BaseFragment<FragmentShipslistBinding>() {

    private val viewModel: ShipsListViewModel by viewModels()

    private lateinit var shipListAdapter: PirateShipsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setBinding(FragmentShipslistBinding.inflate(inflater, container, false))
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setupVisitorsList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pirateShips.observe(viewLifecycleOwner) {
            when {
                it.isSuccessful -> shipListAdapter.updateRecycler(it.content!!)
                it.hasError -> handleError()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPirateShips()
    }

    private fun setupVisitorsList() {
        shipListAdapter = PirateShipsAdapter {
            findNavController().navigate(ShipsListFragmentDirections.actionShipsListFragmentToShipDetailFragment(it))
        }
        binding.rvShips.adapter = shipListAdapter
    }

    private fun handleError() {
        view?.let {
            Snackbar.make(it, getString(R.string.general_error), Snackbar.LENGTH_SHORT).show()
        }
    }
}