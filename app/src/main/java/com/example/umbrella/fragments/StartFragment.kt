
package com.example.umbrella.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umbrella.R



class StartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Handler().postDelayed({fragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer,NavigationFragment())
            ?.commit()},2500)

    }

}


