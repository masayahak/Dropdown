package masayah.myapp.dropdown

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment

// -----------------------------------------------------------------
// スピナー
// -----------------------------------------------------------------
class FragmentSpinner : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_spinner, container, false)

        // スピナーの初期設定
        val spinner: Spinner = view.findViewById(R.id.regular_spinner)
        initSpinner(requireContext(), spinner)

        // スピナーがフォーカスを受けたらキーボードを隠す
        spinner.setOnFocusChangeListener(
            (View.OnFocusChangeListener { v, hasFocus ->
                if (hasFocus) KeyboardUtils.hideKeyboard(v)
            })
        )

        val button : Button = view.findViewById(R.id.button_set_regular)
        button.setOnClickListener() {
            // サンプルなので固定で「洋食」をセット。
            // 本来はDBからの取得値などで初期表示値をセット。
            setSpinnerSelection(spinner, "洋食")
        }

        val getButton : Button = view.findViewById(R.id.button_get_regular)
        getButton.setOnClickListener() {
            val textView : TextView = view.findViewById(R.id.regular_get_textview)
            // スピナーの現在選択されている値を取得
            textView.text = spinner.selectedItem as String
        }

        return view
    }

    // メニューのカテゴリー用スピナーの初期設定
    private fun initSpinner(content : Context, spinner: Spinner) {
        ArrayAdapter.createFromResource(
            content,
            // ドロップダウンに表示される値の配列
            R.array.foodType_array,  // values/array.xmlにて定義
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    // スピナー（ドロップダウンボックス）へ指定された値をセットする
    private fun setSpinnerSelection(spinner: Spinner, item: String) {
        val adapter = spinner.adapter
        var index = 0
        for (i in 0 until adapter.count) {
            if (adapter.getItem(i) == item) {
                index = i
                break
            }
        }
        spinner.setSelection(index)
    }
}