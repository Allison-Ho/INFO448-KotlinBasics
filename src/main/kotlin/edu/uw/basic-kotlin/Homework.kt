package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    when(arg) {
        is String -> {
            return if (arg == "Hello") "world" else "Say what?"}

        is Int -> {
            when(arg){
                0 -> return "zero"
                1 -> return "one"
                in 2..10 -> return "low number"
                else -> return "a number"
            }
        }

        else -> return "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int = lhs + rhs

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int = lhs - rhs

// write a "mathOp" function that takes two Ints and a function (that takes
//two Ints and returns an Int), returns an Int, and applies the
//passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int) = op(lhs, rhs)

// write a class "Person" with first name, last name and age
class Person(var firstName: String, var lastName: String, var age: Int){
     val debugString: String
        get() = "[Person firstName:${this.firstName} lastName:${this.lastName} age:${this.age}]"
}

// write a class "Money"
class Money(val amount: Int, val currency: String){
    init{
        require(amount > 0){
            "amount can't be less than 0"
        }

        require(currency in arrayOf("USD", "EUR", "CAN", "GBP")){
            "invalid currency"
        }
    }

    fun convert(convertCurr: String): Money {
        require(currency in arrayOf("USD", "EUR", "CAN", "GBP")){
            "invalid currency"
        }

        return if (this.currency == convertCurr){
            Money(this.amount, convertCurr)
        }

        else return when (Pair(this.currency, convertCurr)) {
            Pair("USD", "GBP") -> Money((this.amount * .5).toInt(), "GBP")
            Pair("USD", "EUR") -> Money((this.amount * 1.5).toInt(), "EUR")
            Pair("USD", "CAN") -> Money((this.amount * 1.25).toInt(), "CAN")
            Pair("GBP", "USD") -> Money((this.amount * 2).toInt(), "USD")
            Pair("EUR", "USD") -> Money((this.amount * .75).toInt(), "USD")
            Pair("CAN", "USD") -> Money((this.amount * 5 / 4).toInt(), "USD")
            Pair("EUR", "GBP") -> Money((this.amount * 1 / 3).toInt(), "GBP")
            Pair("GBP", "EUR") -> Money((this.amount * 3).toInt(), "EUR")
            Pair("CAN", "EUR") -> Money((this.amount * 5 / 6).toInt(), "EUR")
            Pair("EUR", "CAN") -> Money((this.amount * 6 / 5).toInt(), "CAN")
            Pair("CAN", "GBP") -> Money((this.amount * 5 / 2).toInt(), "GBP")
            Pair("GBP", "CAN") -> Money((this.amount * 2 / 5).toInt(), "CAN")
            else -> convert(convertCurr)
        }
    }

    operator fun plus(other: Money): Money {
        return Money(this.amount + other.convert(this.currency).amount, this.currency)
    }
}
