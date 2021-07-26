package com.example.qaytaqayta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add.view.*

class MainActivity : AppCompatActivity() {
    var meAdapter = MyAdapter()
    private lateinit var dao: MyDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = meAdapter
        meAdapter.onMoreClicker = { user, view ->
            val popupMenu: PopupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.add_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editItem -> {

                        onEdit(user)
                    }
                    R.id.deleteItem -> {
                        dao.delete(user)
                        meAdapter.removeItem(user)
                    }
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
        setData()

    }

    private fun onEdit(user: User) {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null, false)
        view.etTextName.setText(user.username)
        view.etTextSurname.setText(user.surname)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit user")
            .setView(view)
            .setPositiveButton("Add") { dialog, which ->
                user.username = view.etTextName.text.toString()
                user.surname = view.etTextSurname.text.toString()
                dao.update(user)
                setData()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
        dialog.show()
    }

    fun setData() {
        dao = MyDatabase.getInstance(this).usernameDao()
        meAdapter.models = dao.getAllUsername()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.menu.add_menu -> {
                val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null, false)
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Adding new user")
                    .setView(view)
                    .setPositiveButton("Add") { dialog, which ->
                        val user = User(
                            username = view.etTextName.text.toString(),
                            surname = view.etTextSurname.text.toString()
                        )
                        addToDb(user)
                    }
                    .setNegativeButton("Cancel") { dialog, which ->
                        dialog.dismiss()
                    }
                dialog.show()
                return true
            }
        }
        return true
    }

    fun addToDb(user: User) {
        dao.insert(user)
    }
}