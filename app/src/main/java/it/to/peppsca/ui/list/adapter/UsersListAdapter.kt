package it.to.peppsca.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.djungle.mvvmi.ui.list.adapter.OnItemSelected
import it.to.domain.entity.PirateShipEntity
import it.to.peppsca.R
import it.to.peppsca.databinding.ItemPirateShipBinding

/**
 * [UsersListAdapter] is the adapter that handles the user list
 */
class UsersListAdapter(
    private val callback: OnItemSelected<PirateShipEntity>
) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    private var pirateShips = listOf<PirateShipEntity>()

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
     * @param newUsers is the list of Item to populate
     */
    fun updateRecycler(newUsers: List<PirateShipEntity>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            val oldUsers = pirateShips

            override fun getOldListSize(): Int {
                return oldUsers.size
            }

            override fun getNewListSize(): Int {
                return newUsers.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldUsers[oldItemPosition].id == newUsers[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldUsers[oldItemPosition] == newUsers[newItemPosition]
            }
        })
        pirateShips = newUsers
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

        fun setPirateShip(pirateShip: PirateShipEntity) {
            binding.pirateItem = pirateShip
        }
    }
}
