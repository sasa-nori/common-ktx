package net.newstyleservice.example.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.button_second
import net.newstyleservice.example.R
import net.newstyleservice.example.R.dimen
import net.newstyleservice.example.R.layout
import ss_n.common_ktx.extension.inflate
import ss_n.common_ktx.extension.setMarginRes
import ss_n.common_ktx.extension.setOnSingleClickListener

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(layoutInflater = inflater, layout = layout.fragment_second)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_second.setMarginRes(topRes = dimen.margin)
        button_second.setOnSingleClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}
