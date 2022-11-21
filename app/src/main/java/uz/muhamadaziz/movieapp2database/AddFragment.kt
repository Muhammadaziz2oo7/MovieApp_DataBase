package uz.muhamadaziz.movieapp2database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.muhamadaziz.movieapp2database.databinding.FragmentAddBinding
import uz.muhamadaziz.movieapp2database.db.MyDataBase
import uz.muhamadaziz.movieapp2database.db.User


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var myDataBase: MyDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        binding.addSave.setOnClickListener {
            if (binding.addMovie.text.isNotEmpty() && binding.authorMovie.text.isNotEmpty() && binding.aboutMovie.text.isNotEmpty() && binding.dataMovie.text.isNotEmpty()) {

                val user = User(
                    binding.addMovie.text.toString(),
                    binding.authorMovie.text.toString(),
                    binding.aboutMovie.text.toString(),
                    binding.dataMovie.text.toString()
                )
                myDataBase = MyDataBase(binding.root.context)
                myDataBase.createUser(user)
                findNavController().popBackStack()
            } else Toast.makeText(binding.root.context, "Write Something", Toast.LENGTH_SHORT)
                .show()
        }
            return binding.root
    }
}