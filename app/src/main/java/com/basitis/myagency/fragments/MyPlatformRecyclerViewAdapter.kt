package com.basitis.myagency.fragments

import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import com.basitis.myagency.R

import com.basitis.myagency.fragments.PlatformFragment.OnListFragmentInteractionListener
import com.basitis.myagency.fragments.dummy.DummyContent.DummyItem
import com.basitis.myagency.models.Platform

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPlatformRecyclerViewAdapter(private var mValues: List<Platform>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyPlatformRecyclerViewAdapter.ViewHolder>() {
    var sparcearray: SparseBooleanArray = SparseBooleanArray();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_platform, parent, false)
        var holder = ViewHolder(view);
        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(getItem(holder.adapterPosition))
        }
        holder.cbPlatform.setOnClickListener((View.OnClickListener {
            if ((view as CheckedTextView).isChecked) {
                sparcearray.put(getItem(holder.adapterPosition).id.toInt(), true)
            }else{
                sparcearray.delete(getItem(holder.adapterPosition).id.toInt())
            }
        }))
        return holder
    }

    private fun getSparceArray(): SparseBooleanArray{
        return sparcearray
    }

    private fun getItem(adapterPosition: Int): Platform {
        return mValues[adapterPosition];
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.cbPlatform.text  = mValues[position].name
        holder.cbPlatform.isChecked = (sparcearray.get(mValues[position].id.toInt()))
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val cbPlatform: CheckedTextView
        var mItem: Platform? = null

        init {
            cbPlatform = mView.findViewById<CheckedTextView>(R.id.cbPlatform) as CheckedTextView
        }

        override fun toString(): String {
            return super.toString()
        }
    }

    fun setData(platforms: List<Platform>) {
        mValues = platforms;
    }
}
