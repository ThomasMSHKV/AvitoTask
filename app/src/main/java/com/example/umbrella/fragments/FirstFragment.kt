package com.example.umbrella.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.umbrella.R
import com.example.umbrella.data.WeatherRepository
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.*


class FirstFragment : Fragment(), CoroutineScope {

    override val coroutineContext = Dispatchers.Main


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val infoFragment = InfoFragment()
        val repository = WeatherRepository()
        val bottomsheetFragment = BottomSheet_Fragment()

        launch {
            val weather = repository.getData().await()

            withContext(Dispatchers.Main) {
                loader.visibility = View.GONE
                firstScreen.visibility = View.VISIBLE

            }
            weather?.let {
                description_info.text = getString(R.string.sky).format()
                address.text = getString(R.string.city).format()
                temp.text = getString(R.string.temp).format()
                sunrise.text = getString(R.string.sunrise).format()
                sunset.text = getString(R.string.sunset).format()
                humidity.text = getString(R.string.humidity).format()
                pressure.text = getString(R.string.pressure).format()
                wind.text = getString(R.string.wind).format()

            }

        }


        BottomSheetBehavior.STATE_COLLAPSED
        sheet_btn.visibility = View.VISIBLE
        sheet_btn.setOnClickListener {
            bottomsheetFragment.show(
                requireActivity().supportFragmentManager,
                "BottomSheetFragment"
            )


        }

        info_bt.setOnClickListener {
            infoFragment.arguments = Bundle().also {
                it.putInt("key", 1)
            }
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, infoFragment)
                ?.addToBackStack(null)
                ?.commit()

        }

    }

}







