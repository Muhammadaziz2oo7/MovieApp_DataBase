package uz.muhamadaziz.movieapp2database

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.muhamadaziz.movieapp2database.databinding.FragmentAboutBinding
import uz.muhamadaziz.movieapp2database.db.MyDataBase


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private lateinit var myDataBase: MyDataBase

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(layoutInflater)
        binding.apply {
            myDataBase = MyDataBase(root.context)
            val list = myDataBase.readUser()

            aboutName.text = "Movie name: ${list[MyData.myPos].name}"
            aboutAuthors.text = "Movie authors: ${list[MyData.myPos].author}"
            aboutAbout.text = "About movie: ${list[MyData.myPos].about}"
            aboutData.text = "Movie date: ${list[MyData.myPos].data}"
            close.setOnClickListener {
                findNavController().popBackStack()
            }

            return root
        }
    }
}
