package uz.muhamadaziz.movieapp2database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.muhamadaziz.movieapp2database.databinding.FragmentHomeBinding
import uz.muhamadaziz.movieapp2database.db.MyDataBase
import uz.muhamadaziz.movieapp2database.db.User

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var myDBHelper: MyDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {
            myDBHelper = MyDataBase(root.context)
            val list = myDBHelper.readUser()

            imageAdd.setOnClickListener {
                findNavController().navigate(R.id.addFragment)
            }

            homeAdapter = HomeAdapter(list, object : HomeAdapter.OnClickListener {
                override fun editMovie(user: User, position: Int) {
                    MyData.myPos = position
                    findNavController().navigate(R.id.editFragment, bundleOf("userInfo" to user, "id" to user.id))
                }

                override fun deleteMovie(user: User, position: Int) {
                    myDBHelper.deleteUser(user)
                    list.removeAt(position)
                    homeAdapter.notifyItemRemoved(position)
                    homeAdapter.notifyItemRangeChanged(position, list.size)
                }

                override fun onClick(position: Int) {
                    MyData.myPos = position
                    findNavController().navigate(R.id.aboutFragment)
                }
            })
            myRv.adapter = homeAdapter

            return binding.root
        }
      }
}