package com.example.fooddeliveryaggregator.core.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.commit
import com.example.fooddeliveryaggregator.R

@JvmOverloads
fun FragmentActivity.addFragment(
    fragment: Fragment,
    container: Int = R.id.fmt_container,
    addToBackStack: Boolean = false,
    tag: String = fragment.javaClass.simpleName,
    allowStateLoss: Boolean = false
) = supportFragmentManager.commit(allowStateLoss) {
    add(container, fragment, tag)
    if (addToBackStack) addToBackStack(null)
}

@JvmOverloads
fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    container: Int = R.id.fmt_container,
    tag: String = fragment.javaClass.simpleName,
    allowStateLoss: Boolean = false
) = supportFragmentManager.commit(allowStateLoss)  {
    replace(container, fragment, tag)
    addToBackStack(null)
}