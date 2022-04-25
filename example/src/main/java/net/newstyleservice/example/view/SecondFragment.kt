package net.newstyleservice.example.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.newstyleservice.example.R
import net.newstyleservice.example.R.dimen
import net.newstyleservice.example.databinding.FragmentSecondBinding
import ss_n.common_ktx.extension.setMarginRes
import ss_n.common_ktx.extension.setOnSingleClickListener

class SecondFragment : Fragment() {

    private var binding: FragmentSecondBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.buttonSecond?.setMarginRes(topRes = dimen.margin)
        binding?.buttonSecond?.setOnSingleClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
