package net.newstyleservice.example.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.button_first
import net.newstyleservice.example.R
import net.newstyleservice.example.R.layout
import ss_n.common_ktx.extension.inflate
import ss_n.common_ktx.extension.setMargin
import ss_n.common_ktx.extension.setOnSingleClickListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(layoutInflater = inflater, layout = layout.fragment_first)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_first.setMargin(top = 100)
        button_first.setOnSingleClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}
