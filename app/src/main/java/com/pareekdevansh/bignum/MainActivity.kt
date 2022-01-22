package com.pareekdevansh.bignum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    // keeping track of score
    private var counter : Int = 0
    private var score : Int = 0
    private var limit : Int = 15
    private val maxValue = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        restartGame()


        // when button 1 is pressed
        binding.number1.setOnClickListener {
            oneIsPressed = true
            updateScore()
        }

        //when button 2 is pressed
        binding.number2.setOnClickListener {
            oneIsPressed = false
            updateScore()
        }

        //when Quit button is pressed
        binding.quit.setOnClickListener {
            quitInBetween()
        }

        //when restart is pressed
        binding.restart.setOnClickListener {
            toast("Restarting the game")
            restartGame()
        }



    }

    private fun quitInBetween() {

        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setTitle("!! Game is Not Completed Yet !!")
            setMessage("Your Score : $score / $limit")

            //quit the game
            setNegativeButton("Quit") { _, _ ->
                quitGame()
            }

            setPositiveButton("Cancel"){_,_ ->

            }

        }.create().show()
    }


    // function to update numbers on both of the buttons and to show default image
    private fun generateNumbers() {

        // generating two random values
        num1 = (1..maxValue).random()
        num2 = (1..maxValue).random()
        while(num2!! == num1!!){
            num2 = (1..maxValue).random()
        }

        oneIsGreater = num1!! > num2!!

        // updating the text of the buttons
        binding.number1.text = num1.toString()
        binding.number2.text = num2.toString()
    }

    private fun showScore()
    {
        // shows the score to the user
        binding.score.text = "Score : $score / $counter"
    }
    private fun restartGame(){
        score = 0
        counter = 0

        // show current score = 0
        showScore()
        generateNumbers()

    }

    private fun showNext(){
        if(counter == limit) showFinalOutputDialog ()
        else
        {
            generateNumbers()
        }
    }

    private fun updateScore (){
        // updating the result and result signal
        counter++
        if(oneIsPressed!! == oneIsGreater!! ) {
            score++
            binding.resultSignal.setImageResource(R.drawable.right)
            toast("BigNum!!")
        }
        else {

            binding.resultSignal.setImageResource(R.drawable.wrong)
            toast("Oops!!")

        }
        showScore()
        showNext()

    }


    private fun quitGame()
    {

        toast("Quitting the game")
        finishAffinity()
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    private fun showFinalOutputDialog (){

        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setTitle("!!Game is Completed!!")
            setMessage("Your Score : $score / $limit")

            // restart the game
            setPositiveButton("Restart") { _, _ ->
                toast("Restarting the game")
                restartGame()
            }

            //quit the game
            setNegativeButton("Quit") { _, _ ->
                quitGame()
            }

        }.create().show()
    }


}