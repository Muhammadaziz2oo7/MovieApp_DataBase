package uz.muhamadaziz.movieapp2database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.muhamadaziz.movieapp2database.databinding.ItemRvBinding
import uz.muhamadaziz.movieapp2database.db.User

class HomeAdapter(var list: ArrayList<User>, var onClickListener: OnClickListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner  class ViewHolder(private val binding: ItemRvBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(user: User, position: Int){
            binding.itemTextName.text = user.name
            binding.itemTextAuthor.text = user.author
            binding.itemDate.text = user.data
            binding.item.setOnClickListener {
                onClickListener.onClick(position)
            }
            binding.itemEdit.setOnClickListener {
                onClickListener.editMovie(user, position)
            }
            binding.itemDelete.setOnClickListener {
                onClickListener.deleteMovie(user, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        return ViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun editMovie(user: User, position: Int)
        fun deleteMovie(user: User, position: Int)
        fun onClick(position: Int)

    }
}