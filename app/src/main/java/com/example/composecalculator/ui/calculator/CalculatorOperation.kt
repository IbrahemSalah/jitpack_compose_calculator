package com.example.composecalculator.ui.calculator

sealed class CalculatorOperation(val symbol: String) {
    object Add : CalculatorOperation("+")
    object Multiply : CalculatorOperation("*")
    object Subtract : CalculatorOperation("-")
    object Divide : CalculatorOperation("/")
}
