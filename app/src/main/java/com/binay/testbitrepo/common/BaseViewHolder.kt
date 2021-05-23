package com.binay.testbitrepo.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**

 *
 * Created by Binay on 23/5/21.
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}