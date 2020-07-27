package com.netmeds.fragment

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.netmeds.R
import com.netmeds.ViewModelFactory
import com.netmeds.databinding.FragmentCartBinding
import com.netmeds.fragment.adapter.CartAdapter
import com.netmeds.model.ListModel
import com.netmeds.viewModel.CartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {
    var mViewModel: CartViewModel? = null
    var mAdapter: CartAdapter? = null
    var mList: ArrayList<ListModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        val mBinding: FragmentCartBinding = DataBindingUtil.inflate(
            LayoutInflater.from(container?.context),
            R.layout.fragment_cart,
            container,
            false
        )
        mBinding.lifecycleOwner = viewLifecycleOwner
        activity?.let {
            mViewModel = ViewModelProvider(
                it,
                ViewModelFactory(it.application)
            ).get(CartViewModel::class.java)
        }

        mViewModel?.cartLiveData?.observe(viewLifecycleOwner, Observer {
            if (it.size == 0) {
                noCartImage.visibility = View.VISIBLE
                layoutWithItem.visibility = View.GONE
            } else {
                noCartImage.visibility = View.GONE
                layoutWithItem.visibility = View.VISIBLE
                mList.clear()
                mList.addAll(it)
                mAdapter?.notifyDataSetChanged()
            }
        })

        mViewModel?.cartCostLiveData?.observe(viewLifecycleOwner, Observer {
            textTotalCost.text = context?.getString(R.string.total_cost) + it
        })

        mViewModel?.mPurchaseLiveData?.observe(viewLifecycleOwner, Observer {
            activity?.let {
                noCartImage.setImageDrawable(ContextCompat.getDrawable(it, R.drawable.ic_thanks))
            }
        })
        mBinding.viewModel = mViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = CartAdapter(this, mList)
        recyclerViewCart.adapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            noCartImage.setImageDrawable(
                ContextCompat.getDrawable(
                    it,
                    R.drawable.ic_no_cart
                )
            )
        }
    }

    fun removeFromCart(model: ListModel) {
        mViewModel?.deleteFromDb(model)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.cart).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }
}