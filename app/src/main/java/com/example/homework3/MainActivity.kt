package com.example.homework3

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTextList = listOf(binding.userEmail, binding.userName, binding.firstName, binding.lastName, binding.age)

        binding.saveButton.setOnClickListener{

            val email = binding.userEmail.text.toString()
            val uName = binding.userName.text.toString()
            val lName = binding.lastName.text.toString()
            val fName = binding.firstName.text.toString()
            val uAge = binding.age.text.toString()

            if (checkList(editTextList)) {
                Toast.makeText(applicationContext, "Please Fill Out All Fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(binding.userName.text.length < 10){
                Toast.makeText(applicationContext, "Username Must Include More Than 10 Characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!checkValidEmail(binding.userEmail.text.toString())){
                Toast.makeText(applicationContext, "Invalid Email, try again", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(binding.age.text.toString().toInt() <= 0){
                Toast.makeText(applicationContext, "Number Must Be More Than 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.inputViewGroup.visibility = View.GONE
            binding.outputViewGroup.visibility = View.VISIBLE

            binding.savedEmail.text = email
            binding.savedAge.text = uAge
            binding.savedUsername.text = uName
            binding.savedNameAndLastname.text = fName + lName

        }

        binding.clearButton.setOnLongClickListener{
            binding.userEmail.setText("")
            binding.userName.setText("")
            binding.firstName.setText("")
            binding.lastName.setText("")
            binding.age.setText("")
            true
        }

        binding.againButton.setOnClickListener{
            binding.inputViewGroup.visibility = View.VISIBLE
            binding.outputViewGroup.visibility = View.GONE
        }
    }

    private fun checkList(myList:List<EditText>):Boolean{
        var isEmpty:Boolean = false
        for(item in myList){
            if(item.text.isNullOrBlank()){
                isEmpty = true
            }
        }
        return isEmpty
    }

    private fun checkValidEmail(email:String):Boolean{
        return email.contains("@") && !email.startsWith("@") && !email.endsWith("@")
    }

}