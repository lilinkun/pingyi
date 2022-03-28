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
 * on 2022/3/24  16:45
 * Description：
 */
class PyMainTitle : RelativeLayout {
    private lateinit var titleName: TextView
    private lateinit var ivRight: ImageView

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
        val ta = context.obtainStyledAttributes(attrs, R.styleable.PyMainTitle)
        val text = ta.getString(R.styleable.PyMainTitle_py_title_main_name)
        val rightIcon = ta.getInteger(R.styleable.PyMainTitle_py_title_main_right_icon,0)
        val textSize = ta.getFloat(R.styleable.PyMainTitle_py_title_main_text_size,0f)
        ta.recycle()
        text?.apply {
            titleName.text = this
        }
        rightIcon?.apply {
            ivRight.setBackgroundResource(rightIcon)
        }
        textSize?.let {
            titleName.textSize = textSize
        }

    }

    constructor(context : Context) : super(context){
        init(context)
    }

    private fun init(context: Context) {
        (context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater).inflate(R.layout.py_main_title,this)
        titleName = findViewById(R.id.tv_main_title)
        ivRight = findViewById(R.id.iv_main_title)


    }


}