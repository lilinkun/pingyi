package com.communication.lib_core

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by LG
 * on 2022/3/23  16:59
 * Description：
 */
class PyTextViewRight : RelativeLayout {
    private lateinit var layout: View
    private lateinit var name: TextViewYH
    private lateinit var content: TextViewYH
    private lateinit var icon: ImageView

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
        val ta = context.obtainStyledAttributes(attrs, R.styleable.PyTextViewRight)
        val text = ta.getString(R.styleable.PyTextViewRight_py_text_view_right_name)
        val image = ta.getDrawable(R.styleable.PyTextViewRight_py_text_view_left_image)
        val text_right = ta.getString(R.styleable.PyTextViewRight_py_text_view_right_content)
        ta.recycle()
        text?.apply {
            name.text = this
        }
        image?.apply{
            icon.visibility = View.VISIBLE
            icon.setImageDrawable(image)
        }
        text_right?.apply {
            content.text = this
        }

        content.setWillNotDraw(false)

    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    private fun init(context: Context) {
        (context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater).inflate(R.layout.py_text_view_right, this)

        layout = findViewById(R.id.layout)
        name = findViewById(R.id.name)
        content = findViewById(R.id.content)
        icon = findViewById(R.id.icon)
    }

    fun setName(str: String) {
        this.name.text = str
    }

    fun setContent(str: String) {
        this.content.text = str
    }

    fun getContent(): String {
        return content.text.toString()
    }

}