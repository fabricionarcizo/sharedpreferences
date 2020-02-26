package dk.itu.moapd.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var mPreferences: SharedPreferences

    companion object {
        private const val STATE = "state"
        private const val CHECK = "check"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        mPreferences = activity?.getPreferences(Context.MODE_PRIVATE)!!

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        true_button.setOnClickListener {
            text_view.text = getString(R.string.selected_true)
            savePreferences()
        }

        false_button.setOnClickListener {
            text_view.text = getString(R.string.selected_false)
            savePreferences()
        }

        check_box.setOnClickListener {
            val status = if (check_box.isChecked) "checked" else "unchecked"
            text_view.text = String.format(getString(R.string.selected_bool), status)
            savePreferences()
        }

        retrievePreferences()
    }

    private fun retrievePreferences() {
        check_box.isChecked = mPreferences.getBoolean(CHECK, false)
        text_view.text = mPreferences.getString(STATE, "Hello World!")
    }

    private fun savePreferences() {
        val status = check_box.isChecked
        val text = text_view.text.toString()

        val editor = mPreferences.edit()
        editor.putString(STATE, text)
              .putBoolean(CHECK, status)
              .apply()
    }
}
