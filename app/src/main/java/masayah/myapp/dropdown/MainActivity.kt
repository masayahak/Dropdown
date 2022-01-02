package masayah.myapp.dropdown

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

object KeyboardUtils {
    fun hideKeyboard(focusView: View) {
        val inputMethodManager: InputMethodManager =
            focusView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            focusView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}