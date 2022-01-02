package masayah.myapp.dropdown

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

// -----------------------------------------------------------------
// ドロップダウン
// -----------------------------------------------------------------
class FragmentDropdown : Fragment() {

    // 画面の回転など再描画されたとき用にonCreateViewではなく、onResumeでドロップダウンを初期化する
    override fun onResume() {
        super.onResume()

        // ドロップダウンの初期設定
        val dropdown : AutoCompleteTextView = view?.findViewById(R.id.dropdown_item)!!
        initDropdown(requireContext(), dropdown)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_dropdown, container, false)

        val dropdown : AutoCompleteTextView = view.findViewById(R.id.dropdown_item)

        // ドロップダウンがフォーカスを受けたらキーボードを隠す
        dropdown.onFocusChangeListener = (View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) KeyboardUtils.hideKeyboard(v)
        })

        // ドロップダウンへプログラムから値をセットするサンプル
        val buttonSetDropdown : Button = view.findViewById(R.id.button_set_dropdown)
        buttonSetDropdown.setOnClickListener {
            // サンプルなので固定で「洋食」をセット。
            // 本来はDBからの取得値などで値をセット。
            setDropdownSelection(requireContext(), dropdown, "洋食")
        }

        // ドロップダウンの現在値をプログラムで取得するサンプル
        val buttonGetDropdown : Button = view.findViewById(R.id.button_get_dropdown)
        buttonGetDropdown.setOnClickListener {
            val textView : TextView = view.findViewById(R.id.dropdown_get_textview)
            textView.text = dropdown.text.toString()
        }

        return view
    }

    // ドロップダウンの初期設定
    private fun initDropdown(content : Context, dropdown: AutoCompleteTextView) {
        // 「R.array.foodType_array」は「values/array.xml」で定義
        val foodTypes = resources.getStringArray(R.array.foodType_array)
        // 「R.layout.dropdown_item」は「vlayout/dropdown_item.xml」で定義
        val arrayAdapter = ArrayAdapter(content, R.layout.dropdown_item, foodTypes)
        dropdown.setAdapter(arrayAdapter)
    }

    // 特定の値をドロップダウンにセット
    private fun setDropdownSelection(content : Context, dropdown: AutoCompleteTextView, item : String) {
        dropdown.setText(item)
        // setTextを実行するとドロップダウンへセットしたアダプタがリセットされるので再設定する
        initDropdown(content, dropdown)
        // アダプタ再設定時にドロップダウンが開いた状態になるのを防ぐためにフォーカスを外す
        dropdown.clearFocus()
    }

}