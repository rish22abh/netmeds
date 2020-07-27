package com.netmeds.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.netmeds.R
import com.netmeds.databinding.AdapterCartBinding
import com.netmeds.fragment.CartFragment
import com.netmeds.model.ListModel

class CartAdapter(
    private val mContext: Fragment?,
    private val mList: ArrayList<ListModel>
) :
    RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    inner class MyViewHolder(val mViewDataBinding: AdapterCartBinding) :
        RecyclerView.ViewHolder(mViewDataBinding.root) {
        init {
            mViewDataBinding.click = this@CartAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mViewDataBinding: AdapterCartBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_cart, parent, false
        )
        return MyViewHolder(mViewDataBinding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mViewDataBinding.item = mList[position]
    }

    fun removeFromCart(mModel: ListModel) {
        if (mContext is CartFragment)
            mContext.removeFromCart(mModel)
    }
}