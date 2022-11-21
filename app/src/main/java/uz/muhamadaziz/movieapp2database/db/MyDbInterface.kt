package uz.muhamadaziz.movieapp2database.db

interface MyDbInterface {

    fun createUser(user: User)
    fun readUser(): ArrayList<User>
    fun myUpdate(user: User): Int
    fun deleteUser(user: User)
}