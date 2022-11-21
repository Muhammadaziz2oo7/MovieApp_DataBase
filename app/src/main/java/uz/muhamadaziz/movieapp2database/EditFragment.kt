package uz.muhamadaziz.movieapp2database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.muhamadaziz.movieapp2database.databinding.FragmentEditBinding
import uz.muhamadaziz.movieapp2database.db.MyDataBase
import uz.muhamadaziz.movieapp2database.db.User

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var myDataBase: MyDataBase
    private lateinit var list: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditBinding.inflate(layoutInflater)
        binding.apply {
            myDataBase = MyDataBase(root.context)
            list = myDataBase.readUser()

            editMovie.setText(list[MyData.myPos].name)
            editAuthorMovie.setText(list[MyData.myPos].author)
            aboutMovie.setText(list[MyData.myPos].about)
            dataMovie.setText(list[MyData.myPos].data)

            val user = arguments?.getSerializable("userInfo") as User

            editSave.setOnClickListener {
                val name = editMovie.text.toString()
                val author = editAuthorMovie.text.toString()
                val about = aboutMovie.text.toString()
                val date = dataMovie.text.toString()
                if (name.isNotEmpty() && author.isNotEmpty() && about.isNotEmpty() && date.isNotEmpty()) {
//                    user.id = id.toString().toInt()
                    user.name = name
                    user.author = author
                    user.about = about
                    user.data = date
                    myDataBase.myUpdate(user)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(root.context, "Write Something", Toast.LENGTH_SHORT).show()
                }
            }
            return root
        }
    }
}