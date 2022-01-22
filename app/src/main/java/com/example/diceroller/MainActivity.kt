package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.diceroller.databinding.ActivityMainBinding

import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var number = 0
    private var counter = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {

            btnRoll.setOnClickListener {
                counter = 10
                if (number > 0) {

                    YoYo.with(Techniques.Wobble).duration(2000).repeat(0).playOn(ivDice)
                    changingDice()

                } else {
                    number = rollDice()
                }

            }

            btnHiddenRoll.setOnClickListener {
                if (number != 0) {
                    counter = 10
                    YoYo.with(Techniques.Wobble).duration(2000).repeat(0).playOn(ivDice)
                    changingCheatRoll()
                }

            }

        }


    }

    private fun changingCheatRoll() {
        counter--
        if (counter > 0) {

            Handler(Looper.getMainLooper()).postDelayed({
                rollDice()
                changingCheatRoll()
            }, 200)

        } else {
            number = cheatRoll(number)
        }

    }

    private fun changingDice() {

        counter--
        if (counter > 0) {

            Handler(Looper.getMainLooper()).postDelayed({
                rollDice()
                changingDice()
            }, 200)

        } else {
            number = rollDice()
        }

    }

    private fun cheatRoll(number: Int): Int {

        var cheatedRandom = 0
        if(number == 6) {
            binding.ivDice.setImageResource(R.drawable.dice_6)
        } else {
            cheatedRandom = Random.nextInt(number, 7)
            val rolledDice = when(cheatedRandom) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6


            }
            binding.ivDice.setImageResource(rolledDice)

        }
        return cheatedRandom

    }


    private fun rollDice(): Int {

        val randomInt = Random.nextInt(6) + 1
        val rolledDice = when(randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        binding.ivDice.setImageResource(rolledDice)

        return randomInt
    }
}