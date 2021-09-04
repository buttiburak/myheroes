package com.burak.myheroes.util.customview

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.view.SimpleDraweeView


/**
 * Created by mburak on 5.09.2021.
 */
class TranslateDraweeView: SimpleDraweeView {
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle)

    constructor(context: Context?, hierarchy: GenericDraweeHierarchy?): super(context, hierarchy)

    // to fix translate animation
    override fun animateTransform(matrix: Matrix?) {
        invalidate()
    }
}