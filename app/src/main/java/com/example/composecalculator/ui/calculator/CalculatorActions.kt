package com.example.composecalculator.ui.calculator

sealed class CalculatorActions {
    data class Number(val value: Int) : CalculatorActions()
    data class Operation(val operation: CalculatorOperation) : CalculatorActions()
    object Clear : CalculatorActions()
    object Decimal : CalculatorActions()
    object Delete : CalculatorActions()
    object Calculate : CalculatorActions()
}
