package net.newstyleservice.example.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.newstyleservice.example.R
import net.newstyleservice.example.databinding.FragmentFirstBinding
import ss_n.common_ktx.extension.setMargin
import ss_n.common_ktx.extension.setOnSingleClickListener

class FirstFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.buttonFirst?.setMargin(top = 100)
        binding?.buttonFirst?.setOnSingleClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
