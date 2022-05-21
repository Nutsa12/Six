

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zura.mysuperapp.R

class DashboardFragment: Fragment(R.layout.fragment_dashboard) {
    private lateinit var text: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text = view.findViewById(R.id.numberView)
        text.text = DashboardFragmentArgs.fromBundle(requireArguments()).number.toString()
    }
}