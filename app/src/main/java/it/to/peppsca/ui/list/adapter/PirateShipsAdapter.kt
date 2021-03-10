package it.to.peppsca.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import it.to.peppsca.R
import it.to.peppsca.databinding.ItemPirateShipBinding
import it.to.peppsca.ui.model.PirateShipModel

/**
 * [PirateShipsAdapter] is the adapter that handles the user list
 */
class PirateShipsAdapter(
    private val callback: OnItemSelected<PirateShipModel>
) : RecyclerView.Adapter<PirateShipsAdapter.ViewHolder>() {

    private var pirateShips = listOf<PirateShipModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_pirate_ship, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setPirateShip(pirateShips[position])
    }

    override fun getItemCount() = pirateShips.size

    /**
     * [updateRecycler] will update the recyclerView with a list of UserModel
     *
     * @param newShips is the list of Item to populate
     */
    fun updateRecycler(newShips: List<PirateShipModel>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            val oldShips = pirateShips

            override fun getOldListSize(): Int = oldShips.size


            override fun getNewListSize(): Int = newShips.size


            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldShips[oldItemPosition].id == newShips[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldShips[oldItemPosition] == newShips[newItemPosition]
        })
        pirateShips = newShips
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(var binding: ItemPirateShipBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                binding.pirateItem?.let {
                    callback.onItemSelected(it)
                }
            }
        }

        fun setPirateShip(pirateShip: PirateShipModel) {
            binding.pirateItem = pirateShip
        }
    }
}
