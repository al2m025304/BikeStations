package com.example.bikestations.ui.main.stationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bikestations.R
import com.example.bikestations.databinding.FragmentStationListBinding
import com.example.bikestations.model.MainViewModel

class StationListFragment : Fragment() {

    private lateinit var binding: FragmentStationListBinding

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStationListBinding.inflate(inflater)

        // Observer for the network error.
        sharedViewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MainViewModel
        binding.viewModel = sharedViewModel

        val adapter = StationListAdapter(BikeStationListener { bikeStation ->
            sharedViewModel.onBikeStationClicked(bikeStation)
            findNavController().navigate(R.id.action_stationListFragment_to_stationDetailFragment)
        })
        binding.stationList.adapter = adapter

        binding.stationList.setHasFixedSize(true)
    }

    private fun onNetworkError() {
        if (!sharedViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, R.string.network_error, Toast.LENGTH_LONG).show()
            sharedViewModel.onNetworkErrorShown()
        }
    }

}