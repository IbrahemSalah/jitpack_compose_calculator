package com.example.composecalculator.ui.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

     var calculatorState by mutableStateOf(CalculatorState())
          private set


     fun onAction(action: CalculatorActions) {
          when(action) {
               is CalculatorActions.Number -> enterNumber(action.value)
               is CalculatorActions.Delete -> delete()
               is CalculatorActions.Clear -> calculatorState = CalculatorState()
               is CalculatorActions.Operation -> enterOperation(action.operation)
               is CalculatorActions.Decimal -> enterDecimal()
               is CalculatorActions.Calculate -> calculate()
          }
     }

     private fun enterOperation(operation: CalculatorOperation) {
          if(calculatorState.fNumber.isNotBlank()) {
               calculatorState = calculatorState.copy(operation = operation)
          }
     }

     private fun calculate() {
          val fNumber = calculatorState.fNumber.toDoubleOrNull()
          val sNumber = calculatorState.sNumber.toDoubleOrNull()
          if(fNumber != null && sNumber != null) {
               val result = when(calculatorState.operation) {
                    is CalculatorOperation.Add -> fNumber + sNumber
                    is CalculatorOperation.Subtract -> fNumber - sNumber
                    is CalculatorOperation.Multiply -> fNumber * sNumber
                    is CalculatorOperation.Divide -> fNumber / sNumber
                    null -> return
               }
               calculatorState = calculatorState.copy(
                    fNumber = result.toString().take(15),
                    sNumber = "",
                    operation = null
               )
          }
     }

     private fun delete() {
          when {
               calculatorState.sNumber.isNotBlank() -> calculatorState = calculatorState.copy(
                    sNumber = calculatorState.sNumber.dropLast(1)
               )
               calculatorState.operation != null -> calculatorState = calculatorState.copy(
                    operation = null
               )
               calculatorState.fNumber.isNotBlank() -> calculatorState = calculatorState.copy(
                    fNumber = calculatorState.fNumber.dropLast(1)
               )
          }
     }

     private fun enterDecimal() {
          if(calculatorState.operation == null && !calculatorState.fNumber.contains(".") && calculatorState.fNumber.isNotBlank()) {
               calculatorState = calculatorState.copy(
                    fNumber = calculatorState.fNumber + "."
               )
               return
          } else if(!calculatorState.sNumber.contains(".") && calculatorState.sNumber.isNotBlank()) {
               calculatorState = calculatorState.copy(
                    sNumber = calculatorState.sNumber + "."
               )
          }
     }

     private fun enterNumber(number: Int) {
          if(calculatorState.operation == null) {
               if(calculatorState.fNumber.length >= MAX_NUM_LENGTH) {
                    return
               }
               calculatorState = calculatorState.copy(
                    fNumber = calculatorState.fNumber + number
               )
               return
          }
          if(calculatorState.sNumber.length >= MAX_NUM_LENGTH) {
               return
          }
          calculatorState = calculatorState.copy(
               sNumber = calculatorState.sNumber + number
          )
     }

     companion object {
          private const val MAX_NUM_LENGTH = 8
     }
}