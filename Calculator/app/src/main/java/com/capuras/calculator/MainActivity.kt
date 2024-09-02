package com.capuras.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capuras.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addButton.setOnClickListener {
            val firstNum = binding.firstNum.text.toString()
            val secondNum = binding.secondNum.text.toString()

            if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                val num1 = firstNum.toDoubleOrNull()
                val num2 = secondNum.toDoubleOrNull()

                if (num1 != null && num2 != null) {
                    val sum = num1 + num2

                    val roundedValue = String.format("%.2f", sum)
                    binding.result.text = roundedValue
                } else {
                    binding.result.text = getString(R.string.invalid_number)
                }
            } else {
                binding.result.text = getString(R.string.enter_number)
            }
        }
        binding.subtractButton.setOnClickListener {
            val firstNum = binding.firstNum.text.toString()
            val secondNum = binding.secondNum.text.toString()

            if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                val minuend = firstNum.toDoubleOrNull()
                val subtrahend = secondNum.toDoubleOrNull()

                if (minuend != null && subtrahend != null) {
                    val difference = minuend - subtrahend

                    val roundedValue = String.format("%.2f", difference)
                    binding.result.text = roundedValue
                } else {
                    binding.result.text = getString(R.string.invalid_number)
                }
            } else {
                binding.result.text = getString(R.string.enter_number)
            }
        }
        binding.multiplyButton.setOnClickListener {
            val firstNum = binding.firstNum.text.toString()
            val secondNum = binding.secondNum.text.toString()

            if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                val multiplicand = firstNum.toDoubleOrNull()
                val multiplier = secondNum.toDoubleOrNull()

                if (multiplicand != null && multiplier != null) {
                    val product = multiplicand * multiplier

                    val roundedValue = String.format("%.2f", product)
                    binding.result.text = roundedValue
                } else {
                    binding.result.text = getString(R.string.invalid_number)
                }
            } else {
                binding.result.text = getString(R.string.enter_number)
            }
        }
        binding.divideButton.setOnClickListener {
            val firstNum = binding.firstNum.text.toString()
            val secondNum = binding.secondNum.text.toString()

            if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                val dividend = firstNum.toDoubleOrNull()
                val divisor = secondNum.toDoubleOrNull()

                if (dividend != null && divisor != null) {
                    val quotient = dividend / divisor

                    val roundedValue = String.format("%.2f", quotient)
                    binding.result.text = roundedValue
                } else {
                    binding.result.text = getString(R.string.invalid_number)
                }
            } else {
                binding.result.text = getString(R.string.enter_number)
            }
        }

    }
}