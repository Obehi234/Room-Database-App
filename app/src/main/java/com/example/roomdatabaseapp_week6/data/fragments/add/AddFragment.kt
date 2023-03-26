package com.example.roomdatabaseapp_week6.data.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaseapp_week6.R
import com.example.roomdatabaseapp_week6.data.User
import com.example.roomdatabaseapp_week6.data.UserViewModel

class AddFragment : Fragment() {
//private lateinit var myUserViewModel: UserViewModel
private lateinit var addFirstName: EditText
private lateinit var addLastName: EditText
private lateinit var addAge: EditText
private val myUserViewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFirstName=  view.findViewById(R.id.etFirstname)
        addLastName = view.findViewById(R.id.etLastname)
        addAge = view.findViewById(R.id.etAge)

//        myUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.findViewById<Button>(R.id.add_button).setOnClickListener {
            insertDataToDatabase()
        }

    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName.text.toString()
        val lastName = addLastName.text.toString()
        val age = addAge.text

        if(inputCheck(firstName, lastName, age)){
            //Create user object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            //add data to Database
            myUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}