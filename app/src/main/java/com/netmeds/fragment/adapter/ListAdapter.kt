package com.netmeds.fragment.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.netmeds.R
import com.netmeds.databinding.AdapterListBinding
import com.netmeds.fragment.ListFragment
import com.netmeds.model.ListModel


class ListAdapter(
    private val mContext: Fragment?,
    private var mModelList: ArrayList<ListModel>
) : RecyclerView.Adapter<ListAdapter.MyViewHolder>(), Filterable {
    private var mFilterList: ArrayList<ListModel> = mModelList

    inner class MyViewHolder(val mAdapterListBinding: AdapterListBinding) :
        RecyclerView.ViewHolder(mAdapterListBinding.root) {
        init {
            mAdapterListBinding.click = this@ListAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mViewDataBinding: AdapterListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_list, parent, false
        )
        return MyViewHolder(mViewDataBinding)
    }

    override fun getItemCount(): Int {
        return mFilterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mAdapterListBinding.item = mFilterList[position]
    }

    fun addToCart(
        model: ListModel
    ) {
        if (mContext is ListFragment)
            mContext.addToCart(model)
    }

    override fun getFilter(): Filter {
        return NewFilter()
    }

    inner class NewFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            mFilterList = if(TextUtils.isEmpty(constraint))
                mModelList
            else{
                val filteredList = ArrayList<ListModel>()
                mModelList.forEach {
                    if (it.Keyword?.contains(constraint!!,ignoreCase = true) == true){
                        filteredList.add(it)
                    }
                }
                filteredList
            }
            val filterResults = FilterResults()
            filterResults.values = mFilterList
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            mFilterList = results?.values as ArrayList<ListModel>
            notifyDataSetChanged()
        }
    }
}

