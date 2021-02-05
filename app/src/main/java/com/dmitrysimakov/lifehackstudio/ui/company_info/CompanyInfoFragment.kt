package com.dmitrysimakov.lifehackstudio.ui.company_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dmitrysimakov.lifehackstudio.BASE_URL
import com.dmitrysimakov.lifehackstudio.R
import com.dmitrysimakov.lifehackstudio.data.CompanyInfo
import com.dmitrysimakov.lifehackstudio.databinding.FragmentCompanyInfoBinding
import com.dmitrysimakov.lifehackstudio.ui.MapFragment
import com.dmitrysimakov.lifehackstudio.visibleOrGone
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompanyInfoFragment : Fragment(), OnMapReadyCallback {

    private val args: CompanyInfoFragmentArgs by navArgs()

    private val  viewModel: CompanyInfoViewModel by viewModel()

    private lateinit var binding: FragmentCompanyInfoBinding

    private lateinit var map: GoogleMap
    private lateinit var mapFragment: MapFragment

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyInfoBinding.inflate(inflater)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as MapFragment
        mapFragment.view?.visibility = View.GONE
        mapFragment.setOnTouchListener {
            binding.scroll.requestDisallowInterceptTouchEvent(true)
        }
        mapFragment.getMapAsync(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setCompanyId(args.companyId)
        viewModel.companyInfo.observe(viewLifecycleOwner) { info ->
            binding.img.visibleOrGone(info.img.isNotBlank())
            Glide.with(this).load(BASE_URL + info.img).into(binding.img)

            binding.description.text = info.description

            binding.linkLabel.visibleOrGone(info.www.isNotBlank())
            binding.link.visibleOrGone(info.www.isNotBlank())
            binding.link.text = info.www

            binding.phoneLabel.visibleOrGone(info.phone.isNotBlank())
            binding.phone.visibleOrGone(info.phone.isNotBlank())
            binding.phone.text = info.phone

            updateMap(info)
        }
    }

    private fun updateMap(info: CompanyInfo) {
        if (info.lat != 0.0 || info.lon != 0.0) {
            mapFragment.view?.visibility = View.VISIBLE
            val location = LatLng(info.lat, info.lon)
            map.addMarker(MarkerOptions().position(location).title(info.name))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }
}