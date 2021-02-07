package com.dmitrysimakov.lifehackstudio.ui.companies_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dmitrysimakov.lifehackstudio.ui.companies_list.CompaniesListFragmentDirections.Companion.toCompanyInfoFragment
import com.dmitrysimakov.lifehackstudio.databinding.FragmentCompaniesListBinding
import me.linshen.retrofit2.adapter.ApiSuccessResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompaniesListFragment : Fragment() {

    private val  viewModel: CompaniesListViewModel by viewModel()

    private lateinit var binding: FragmentCompaniesListBinding

    private val adapter = CompaniesListAdapter {
        findNavController().navigate(toCompanyInfoFragment(it.id, it.name))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCompaniesListBinding.inflate(inflater)

        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.companies.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiSuccessResponse -> adapter.submitList(response.body)
            }
        }
    }
}