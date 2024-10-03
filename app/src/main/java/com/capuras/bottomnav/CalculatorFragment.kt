package com.capuras.bottomnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class CalculatorFragment : Fragment() {

    private lateinit var firstNumEditText: EditText
    private lateinit var secondNumEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var addButton: Button
    private lateinit var subtractButton: Button
    private lateinit var multiplyButton: Button
    private lateinit var divideButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        firstNumEditText = view.findViewById(R.id.firstNum)
        secondNumEditText = view.findViewById(R.id.secondNum)
        resultTextView = view.findViewById(R.id.result)
        addButton = view.findViewById(R.id.addButton)
        subtractButton = view.findViewById(R.id.subtractButton)
        multiplyButton = view.findViewById(R.id.multiplyButton)
        divideButton = view.findViewById(R.id.divideButton)

        addButton.setOnClickListener {
            val firstNum = firstNumEditText.text.toString()
            val secondNum = secondNumEditText.text.toString()

            if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                val num1 = firstNum.toDoubleOrNull()
                val num2 = secondNum.toDoubleOrNull()

                if (num1 != null && num2 != null) {
                    val sum = num1 + num2
                    resultTextView.text = String.format("%.2f", sum)
                } else {
                    resultTextView.text = getString(R.string.invalid_number)
                }
            } else {
                resultTextView.text = getString(R.string.enter_number)
            }
        }

        subtractButton.setOnClickListener {
            val firstNum = firstNumEditText.text.toString()
            val secondNum = secondNumEditText.text.toString()

            if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                val minuend = firstNum.toDoubleOrNull()
                val subtrahend = secondNum.toDoubleOrNull()

                if (minuend != null && subtrahend != null) {
                    val difference = minuend - subtrahend
                    resultTextView.text = String.format("%.2f", difference)
                } else {
                    resultTextView.text = getString(R.string.invalid_number)
                }
            } else {
                resultTextView.text = getString(R.string.enter_number)
            }
        }

        multiplyButton.setOnClickListener {
            val firstNum = firstNumEditText.text.toString()
            val secondNum = secondNumEditText.text.toString()

            if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                val multiplicand = firstNum.toDoubleOrNull()
                val multiplier = secondNum.toDoubleOrNull()

                if (multiplicand != null && multiplier != null) {
                    val product = multiplicand * multiplier
                    resultTextView.text = String.format("%.2f", product)
                } else {
                    resultTextView.text = getString(R.string.invalid_number)
                }
            } else {
                resultTextView.text = getString(R.string.enter_number)
            }
        }

        divideButton.setOnClickListener {
            val firstNum = firstNumEditText.text.toString()
            val secondNum = secondNumEditText.text.toString()

            if (firstNum.isNotEmpty() && secondNum.isNotEmpty()) {
                val dividend = firstNum.toDoubleOrNull()
                val divisor = secondNum.toDoubleOrNull()

                if (dividend != null && divisor != null) {
                    val quotient = dividend / divisor
                    resultTextView.text = String.format("%.2f", quotient)
                } else {
                    resultTextView.text = getString(R.string.invalid_number)
                }
            } else {
                resultTextView.text = getString(R.string.enter_number)
            }
        }

        return view
    }
}