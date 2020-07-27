package com.netmeds.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.netmeds.R
import com.netmeds.ViewModelFactory
import com.netmeds.fragment.adapter.ListAdapter
import com.netmeds.model.ListModel
import com.netmeds.viewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private val mList: ArrayList<ListModel> = ArrayList()
    private var mViewModel: ListViewModel? = null
    private var mAdapter: ListAdapter? = null
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
        activity?.let {
            mViewModel = ViewModelProvider(
                it,
                ViewModelFactory(it.application)
            ).get(ListViewModel::class.java)
        }
        mViewModel?.getTestResponseLiveData()?.observe(viewLifecycleOwner, Observer {
            it?.let {
                mList.clear()
                mList.addAll(it)
                mAdapter?.notifyDataSetChanged()
            }
        })
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = ListAdapter(this, mList)
        recyclerViewList.adapter = mAdapter

        searchEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mAdapter?.filter?.filter(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    fun addToCart(model: ListModel) {
        mViewModel?.addToCart(model)
        Toast.makeText(activity,"Item added to cart",Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.cart).isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }
}