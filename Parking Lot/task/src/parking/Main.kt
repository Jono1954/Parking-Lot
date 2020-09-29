package parking

import java.util.Scanner
import kotlin.system.exitProcess

var numberOfParkingSpots = -99

val commands =
        listOf("create", "park", "leave", "status", "reg_by_color", "spot_by_color", "spot_by_reg", "exit")

data class Car(var registration: String = "-99", var color: String = "-99")

var mapOfParkedCars = mutableMapOf<Int, Car>()

val scanner = Scanner(System.`in`)

fun main() {
    while (scanner.hasNextLine()) {
        readCommandLine().apply {
            splitCommandLine(this).let { userInput ->
                when (userInput[0]) {
    
                    "create" -> {
                        numberOfParkingSpots = userInput[1].toInt()
                        createNewParkingLot()
                        println("Created a parking lot with $numberOfParkingSpots spots.")
                    }
    
                    "park" -> {
                        if (numberOfParkingSpots > 0) {
                            val registrationNumber = userInput[1]
                            val color = userInput[2]
            
                            val parkingNumber = getFirstFreeParkingSpot()
                            if (parkingNumber > 0) {
                                assignCarToSpot(parkingNumber, registrationNumber, color)
                                println("$color car parked in spot $parkingNumber.")
                            } else {
                                println("Sorry, the parking lot is full.")
                            }
                        } else {
                            println("Sorry, a parking lot has not been created.")
                        }
                    }
    
                    "leave" -> {
                        if (numberOfParkingSpots > 0) {
                            val parkingNumber = userInput[1].toInt()
                            if (carAlreadyInSpot(parkingNumber)) {
                                removeCarFromSpot(parkingNumber)
                                println("Spot $parkingNumber is free.")
                            } else {
                                println("There is no car in spot $parkingNumber.")
                            }
                        } else {
                            println("Sorry, a parking lot has not been created.")
                        }
                    }
    
                    "status" -> if (numberOfParkingSpots > 0) {
                        printOutParkedCars()
                    } else {
                        println("Sorry, a parking lot has not been created.")
                    }
    
                    "reg_by_color" -> {
                        val color = userInput[1]
                        if (numberOfParkingSpots > 0) {
                            regByColor(color)
                        } else {
                            println("Sorry, a parking lot has not been created.")
                        }
                    }
    
                    "spot_by_color" -> {
                        val color = userInput[1]
                        if (numberOfParkingSpots > 0) {
                            spotByColor(color)
                        } else {
                            println("Sorry, a parking lot has not been created.")
                        }
                    }
    
                    "spot_by_reg" -> {
                        val registration = userInput[1]
                        if (numberOfParkingSpots > 0) {
                            spotByReg(registration)
                        } else {
                            println("Sorry, a parking lot has not been created.")
                        }
                    }
    
                    "exit" -> exitProcess(0)
                    
                    else -> println("Unknown input")
                }
            }
        }
    }
}


private fun readCommandLine(): String {
    return scanner.nextLine()
}

private fun splitCommandLine(input: String): List<String> {
    return input.split(" ")
}

private fun createNewParkingLot() {
    mapOfParkedCars.clear()
    (1..numberOfParkingSpots).forEach { mapOfParkedCars[it] = Car() }
}

private fun getFirstFreeParkingSpot(): Int {
    for ((parkingNumber, car) in mapOfParkedCars) {
        if (car.registration == "-99") {
            return parkingNumber
        }
    }
    return 0
}

private fun carAlreadyInSpot(parkingNumber: Int): Boolean {
    return (mapOfParkedCars[parkingNumber]?.registration != "-99")
}

private fun assignCarToSpot(parkingNumber: Int, registration: String, color: String) {
    mapOfParkedCars[parkingNumber] =
            Car(registration = registration, color = color.capitalize())
}

private fun removeCarFromSpot(parkingNumber: Int) {
    mapOfParkedCars[parkingNumber] = Car()
}

private fun printOutParkedCars() {
    if (mapOfParkedCars.isNullOrEmpty()) {
        println("Parking lot is empty.")
    } else {
        mapOfParkedCars.toSortedMap().forEach { (parking, car) ->
            println("$parking ${car.registration} ${car.color}")
        }
    }
}

private fun regByColor(color: String) {
    val mapOfCars = mapOfParkedCars
            .filterValues { it.color.toLowerCase() == color.toLowerCase() }
            .mapValues { it.value.registration }
            .toSortedMap()
    if (mapOfCars.isNotEmpty()) {
        print(mapOfCars.values.joinToString(", "))
        println()
    } else {
        println("No cars with color ${color.capitalize()} were found.")
    }
}

private fun spotByColor(color: String) {
    val mapOfCars = mapOfParkedCars
            .filterValues { it.color.toLowerCase() == color.toLowerCase() }
            .mapValues { it.value.color }
            .toSortedMap()
    if (mapOfCars.isNotEmpty()) {
        print(mapOfCars.keys.joinToString(", "))
        println()
    } else {
        println("No cars with color ${color.capitalize()} were found.")
    }
}

private fun spotByReg(registration: String) {
    val mapOfCars = mapOfParkedCars
            .filterValues { it.registration.toLowerCase() == registration.toLowerCase() }
            .toSortedMap()
    if (mapOfCars.isNotEmpty()) {
        print(mapOfCars.keys.joinToString(", "))
        println()
    } else {
        println("No cars with registration number ${registration.capitalize()} were found.")
    }
}