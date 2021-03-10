package it.to.peppsca.ui.list.adapter

/**
 * This is interface related to on item selected callback
 */
fun interface OnItemSelected<T> {
    fun onItemSelected(item: T)
}
