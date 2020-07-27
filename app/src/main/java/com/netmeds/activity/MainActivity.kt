package com.netmeds.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.netmeds.R
import com.netmeds.fragment.CartFragment
import com.netmeds.fragment.ListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        if (savedInstanceState == null)
            addFragment(ListFragment(), false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cart) {
            addFragment(CartFragment(), true)
        }
        return true
    }


    private fun addFragment(fragment: Fragment, isBackStack: Boolean) {
        if (isBackStack)
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    fragment
                )
                .addToBackStack(fragment.toString())
                .commit()
        else
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.container,
                    fragment
                )
                .commit()
    }
}
