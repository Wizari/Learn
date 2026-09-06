package com.gmail.wizaripost.learning.claude.tutorial

/*
 * СЛОВАРЬ ТЕРМИНОВ (RU ↔ EN)
 * --------------------------------------------------------------
 * класс                         — class
 * объект / экземпляр            — object / instance
 * основной конструктор          — primary constructor
 * вторичный конструктор         — secondary constructor
 * параметр конструктора         — constructor parameter
 * параметр метода               — method parameter
 * аргумент                      — argument (конкретное значение, переданное в параметр)
 * поле / свойство класса        — field / property
 * изменяемое свойство (var)     — mutable property
 * неизменяемое свойство (val)   — read-only property
 * локальная переменная          — local variable
 * метод / функция               — method / function
 * тип возвращаемого значения    — return type
 * модификатор видимости         — visibility modifier (public/private/protected/internal)
 * блок инициализации            — init block
 * интерфейс                     — interface
 * абстрактный класс             — abstract class
 * родительский класс/суперкласс — superclass / parent class
 * дочерний класс/наследник      — subclass / child class
 * наследование                  — inheritance
 * переопределение               — override
 * компаньон-объект              — companion object
 * дата-класс                    — data class
 * класс-перечисление            — enum class
 * закрытый класс                — sealed class
 * обобщение / параметр типа     — generic / type parameter
 * nullable-тип                  — nullable type (тип, допускающий null)
 * функция-расширение            — extension function
 * лямбда-выражение              — lambda expression
 * --------------------------------------------------------------
 */

// RU: интерфейс — EN: interface
interface Greeter {
    // RU: абстрактный метод (без тела, каждый реализующий класс обязан его написать)
    // EN: abstract method (no body, must be implemented by any class using this interface)
    fun greet(): String
}

// RU: класс, объявляющий основной конструктор в заголовке
// EN: class declaring its primary constructor right in the header
class Person(
    // RU: параметр конструктора + одновременно поле класса (val = неизменяемое свойство)
    // EN: constructor parameter that is also a class field (val = read-only property)
    val name: String,

    // RU: параметр конструктора + поле класса (var = изменяемое свойство)
    // EN: constructor parameter that is also a class field (var = mutable property)
    var age: Int,

    // RU: параметр конструктора со значением по умолчанию + модификатор видимости private
    // EN: constructor parameter with a default value + visibility modifier "private"
    private val id: Long = 0L,
) : Greeter {
    // RU: наследование/реализация интерфейса Greeter — EN: inheriting/implementing the Greeter interface

    // RU: блок инициализации — выполняется при создании объекта
    // EN: init block — runs when the object is created
    init {
        require(age >= 0) { "Возраст не может быть отрицательным" }
    }

    // RU: поле класса, объявленное не в конструкторе, а прямо в теле класса
    // EN: a class field declared in the class body (not via the constructor)
    var nickname: String? = null // RU: nullable-тип — EN: nullable type (может быть null / can hold null)

    // RU: вторичный конструктор — вызывает основной через this(...)
    // EN: secondary constructor — delegates to the primary one via this(...)
    constructor(name: String) : this(name, age = 0)

    // RU: метод, переопределяющий метод интерфейса — EN: method overriding the interface's method
    override fun greet(): String {
        // RU: локальная переменная — существует только внутри метода
        // EN: local variable — exists only inside this method
        val greeting = "Hello, $name"
        return greeting
    }

    // RU: метод с параметром и типом возвращаемого значения
    // EN: method with a parameter and a return type
    fun haveBirthday(
        // RU: параметр метода — EN: method parameter
        yearsPassed: Int,
    ): Int {
        // RU: аргумент — конкретное число, которое подставят вместо yearsPassed при вызове
        // EN: argument — the concrete value substituted for yearsPassed when calling
        age += yearsPassed
        return age
    }

    // RU: компаньон-объект — общие для всех объектов класса члены (аналог static в Java)
    // EN: companion object — members shared by all instances (like "static" in Java)
    companion object {
        // RU: константа компаньон-объекта — EN: companion object constant
        const val SPECIES = "Homo sapiens"
    }
}

// RU: абстрактный класс — нельзя создать объект напрямую, только через наследника
// EN: abstract class — cannot be instantiated directly, only through a subclass
abstract class Shape {
    // RU: абстрактный метод — EN: abstract method
    abstract fun area(): Double

    // RU: обычный (не абстрактный) метод с готовой реализацией
    // EN: regular (non-abstract) method with a ready implementation
    fun describe(): String = "Площадь: ${area()}"
}

// RU: дочерний класс (наследник) — EN: subclass (child class)
class Circle(
    private val radius: Double,
) : Shape() { // RU: родительский класс — EN: superclass / parent class
    override fun area(): Double = Math.PI * radius * radius
}

// RU: дата-класс — авто equals/hashCode/toString/copy, удобен для DTO
// EN: data class — auto-generated equals/hashCode/toString/copy, handy for DTOs
data class Point(
    val x: Int,
    val y: Int,
)

// RU: класс-перечисление — фиксированный набор именованных значений
// EN: enum class — a fixed set of named values
enum class Status {
    ACTIVE,
    INACTIVE,
}

// RU: закрытый класс — заранее известный и закрытый набор вариантов-подклассов
// EN: sealed class — a known, closed set of subclass variants
sealed class ApiResult

// RU: вариант закрытого класса, несущий данные — EN: sealed class variant carrying data
data class Success(
    val data: String,
) : ApiResult()

// RU: вариант закрытого класса без данных (object — единственный экземпляр)
// EN: sealed class variant without data (object — a single shared instance)
object Loading : ApiResult()

// RU: обобщённый (generic) класс — T это параметр типа, подставляется при использовании
// EN: generic class — T is a type parameter, substituted when the class is used
class Box<T>(
    val content: T,
)

// RU: функция-расширение — добавляет метод существующему типу (тут — Int) без наследования
// EN: extension function — adds a method to an existing type (here — Int) without inheritance
fun Int.isEven(): Boolean = this % 2 == 0

fun main() {
    // RU: создание объекта / экземпляра класса — EN: creating an object / instance of the class
    val alice = Person(name = "Alice", age = 30)
    // ^ RU: "Alice" и 30 — это аргументы, переданные в параметры name и age
    // ^ EN: "Alice" and 30 are the arguments passed into the name and age parameters

    alice.haveBirthday(1) // RU: 1 — аргумент для параметра yearsPassed — EN: 1 is the argument for parameter yearsPassed
    println(alice.greet())

    val box = Box("текст") // RU: T подставился как String — EN: T got substituted with String
    println(box.content)

    println(4.isEven()) // RU: вызов функции-расширения — EN: calling the extension function

    // RU: лямбда-выражение, присвоенное локальной переменной
    // EN: lambda expression assigned to a local variable
    val square: (Int) -> Int = { n -> n * n }
    println(square(5))
}
