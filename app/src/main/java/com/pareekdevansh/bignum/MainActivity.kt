package com.pareekdevansh.bignum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pareekdevansh.bignum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //num1 will be printed on button 1
    private var num1: Int? = null

    // num2 will be printed on button 2
    private var num2: Int? = null

    // var to keep track of which number 1 is greater or not
    private var oneIsGreater: Boolean? = null

    // one was pressed or not
    private var oneIsPressed: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        reset()

        // when button 1 is pressed
        binding.number1.setOnClickListener {
            oneIsPressed = true
            showResult()
        }

        //when button 2 is pressed
        binding.number2.setOnClickListener {
            oneIsPressed = false
            showResult()
        }

        //when reset button is pressed
        binding.reset.setOnClickListener {
            reset()
        }


    }

    // function to update numbers on both of the buttons
    private fun generateNumbers() {
        // generating two random values
        num1 = (1..100).random()
        num2 = (1..100).random()

        oneIsGreater = num1!! > num2!!

        binding.number1.text = num1.toString()
        binding.number2.text = num2.toString()
    }

    private fun showResult()
    {
        // updating the result and result signal
        if(oneIsPressed!! == oneIsGreater!! ) {
            binding.result.text = getString(R.string.correct)
            binding.resultSignal.setImageResource(R.drawable.right)
        }
        else {
            binding.result.text = getString(R.string.wrong)
            binding.resultSignal.setImageResource(R.drawable.wrong)
        }

    }

    private fun reset(){
        binding.resultSignal.setImageResource(R.drawable.right_wrong)
        binding.result.text = getString(R.string.result)
        generateNumbers()
    }
}