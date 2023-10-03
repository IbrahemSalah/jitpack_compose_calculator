package com.example.composecalculator.ui.calculator

data class CalculatorState(
    val fNumber: String = "",
    val sNumber: String = "",
    val operation: CalculatorOperation? = null,
)
