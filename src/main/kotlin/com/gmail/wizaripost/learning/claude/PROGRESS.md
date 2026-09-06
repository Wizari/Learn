# Журнал прогресса

Полная копия каждого урока: теория, задания, решения и разбор — чтобы можно
было перечитать урок целиком, а не только итоговую выжимку. Актуальный код
всегда смотри в `.kt`-файлах, здесь — учебный материал в развёрнутом виде.

## Оглавление

- [Lesson 001 — Классы, объекты, инкапсуляция ✅](#lesson-001--классы-объекты-инкапсуляция-)
- [Lesson 002 — Наследование, интерфейсы, abstract class ✅](#lesson-002--наследование-интерфейсы-abstract-class-)
- [Lesson 003 — data class, sealed class, enum class, null safety 🔶](#lesson-003--data-class-sealed-class-enum-class-null-safety-)

---

## Lesson 001 — Классы, объекты, инкапсуляция ✅

### Теория

**Класс и объект.** Класс — это чертёж, объект — то, что по этому чертежу
построено.

```kotlin
class Car(val brand: String, var speed: Int) {
    fun accelerate() {
        speed += 10
        println("$brand разгоняется до $speed км/ч")
    }
}

fun main() {
    val myCar = Car("Toyota", 0)
    myCar.accelerate()  // Toyota разгоняется до 10 км/ч
}
```

Важные моменты, которые в Kotlin работают не как в Java:

**1. Primary constructor прямо в заголовке класса.**
```kotlin
class Car(val brand: String, var speed: Int)
```
Это сразу и объявление полей, и конструктор. `val` — неизменяемое поле (только
чтение снаружи), `var` — изменяемое. Никакого boilerplate с полями +
конструктором + геттерами, как в Java.

**2. init-блок** — если нужна логика при создании:
```kotlin
class Car(val brand: String, var speed: Int) {
    init {
        require(speed >= 0) { "Скорость не может быть отрицательной" }
    }
}
```

**3. Secondary constructor** — редко нужен, но бывает:
```kotlin
class Car(val brand: String, var speed: Int) {
    constructor(brand: String) : this(brand, 0)
}
```

**Инкапсуляция.** По умолчанию всё `public`. Чтобы скрыть детали:
```kotlin
class BankAccount(private var balance: Int) {
    fun deposit(amount: Int) {
        if (amount > 0) balance += amount
    }

    fun getBalance(): Int = balance
}
```
Снаружи нельзя написать `account.balance = 1000000` напрямую — только через
методы, которые контролируют логику.

### Задания

**Задача 1.** Создай класс `Student` с полями `name` (val) и `grades`
(изменяемый список Int, изначально пустой). Добавь метод `addGrade(grade: Int)`,
который добавляет оценку в список, и метод `averageGrade(): Double`, который
считает среднее (если оценок нет — верни `0.0`).

**Задача 2.** Создай класс `Rectangle(width, height)` с приватными полями.
Добавь методы `area()` и `perimeter()`. Добавь метод `isSquare(): Boolean`.

**Задача 3.** Создай класс `Wallet` с приватным балансом. Добавь `deposit(amount)`
и `withdraw(amount)`. `withdraw` не должен позволять уйти в минус — вместо этого
пусть выбрасывает исключение `IllegalArgumentException` с понятным сообщением,
если денег не хватает.

### Решения (финальная версия после разбора)

**Student**
```kotlin
class Student(
    private val name: String,
    private val grades: MutableList<Int> = mutableListOf(),
) {
    fun addGrade(grade: Int) {
        grades.add(grade)
    }

    fun averageGrade(): Double {
        if (grades.isEmpty()) return 0.0
        return grades.average()
    }
}
```

**Rectangle**
```kotlin
class Rectangle(
    private val width: Int,
    private val height: Int,
) {
    fun area(): Int = width * height
    fun perimeter(): Int = 2 * (width + height)
    fun isSquare(): Boolean = width == height
}
```

**Wallet**
```kotlin
class Wallet(
    private var balance: Int,
) {
    fun deposit(amount: Int) {
        if (amount <= 0) throw IllegalArgumentException("Amount must be greater than zero")
        balance += amount
    }

    fun withdraw(amount: Int) {
        if (balance < amount) throw IllegalArgumentException("Недостаточно средств на счету!")
        balance -= amount
    }
}
```

### Разбор

- **Student** — первая версия требовала руками передавать список
  (`Student("Аня", mutableListOf())`), что неудобно. Поправлено: значение по
  умолчанию `= mutableListOf()`. Важная идиома: `val` для коллекции не значит
  "неизменяемая коллекция", значит "неизменяемая ссылка на коллекцию" — сама
  коллекция мутируется через `.add()`. Ручной цикл суммы оценок заменён на
  готовую `grades.average()`. Правило: прежде чем писать цикл руками, почти
  всегда есть готовая функция (`sum()`, `average()`, `max()`, `count { }` и т.д.).
- **Rectangle** — исходная версия `isSquare()` была написана как
  `if (width == height) return true else return false`. Это красный флаг:
  само выражение `width == height` уже `Boolean`, конструкция
  `if (x) return true else return false` всегда избыточна — заменено на
  `width == height`.
- **Wallet** — логика `withdraw` изначально была верной. Добавлены две правки:
  `deposit` теперь проверяет `amount > 0` (раньше можно было положить
  отрицательную сумму и обналичить баланс); `withdraw` больше не возвращает
  `amount` — вызывающий код и так знает, сколько он снял, возвращаемое значение
  должно нести новую информацию, а не дублировать то, что уже известно
  вызывающему. Также была замечена проблема с кодировкой кириллицы в сообщении
  об ошибке (текст превращался в вопросики) — на логику не влияет, но стоит
  иметь в виду при работе с кириллицей в файлах (обычно проблема в encoding
  файла/консоли, не в самом Kotlin).

---

## Lesson 002 — Наследование, интерфейсы, abstract class ✅

### Теория

**Наследование.** В Kotlin классы по умолчанию `final` — нельзя унаследоваться,
пока явно не разрешишь через `open`:
```kotlin
open class Animal(val name: String) {
    open fun makeSound(): String = "..."
}

class Dog(name: String) : Animal(name) {
    override fun makeSound(): String = "Гав!"
}
```
Две вещи: `open` нужен и на классе, и на методе, который хочешь переопределять;
`override` обязателен — без него не скомпилируется. Это осознанное решение
дизайна Kotlin: наследование — не то, что должно быть возможно "по умолчанию",
а то, что автор класса явно разрешает.

**Интерфейсы.**
```kotlin
interface Movable {
    fun move(): String
    fun describe(): String = "Я умею двигаться" // можно дать реализацию по умолчанию
}

class Dog(name: String) : Animal(name), Movable {
    override fun move(): String = "$name бежит"
}
```
Ключевое отличие от абстрактного класса: класс может реализовать много
интерфейсов, но унаследоваться только от одного класса. Интерфейс — контракт
"что уметь делать", класс — "чем являться".

**Abstract class.** Промежуточный вариант — когда хочешь и общую реализацию
дать, и заставить наследников что-то реализовать самим:
```kotlin
abstract class Shape {
    abstract fun area(): Double
    fun describe(): String = "Площадь фигуры: ${area()}"  // не abstract — есть реализация
}

class Circle(val radius: Double) : Shape() {
    override fun area(): Double = Math.PI * radius * radius
}
```
`abstract class` нельзя создать напрямую (`Shape()` — ошибка), только через
наследников.

### Задания

**Задача 1.** Есть `abstract class Employee(val name: String, val baseSalary: Double)`
с абстрактным методом `calculateSalary(): Double`. Сделай два класса-наследника:
`Manager` (зарплата = baseSalary + бонус, бонус передаётся в конструкторе) и
`Developer` (зарплата = baseSalary, без изменений). Добавь в `Employee`
неабстрактный метод `printPaycheck()`, который печатает имя и результат
`calculateSalary()`.

**Задача 2.** Создай интерфейс `Drawable` с методом `draw(): String`. Сделай
классы `Circle` и `Square`, реализующие его (наследование от Rectangle/своих
полей не нужно — просто отдельные классы с полями и `draw()`, который
возвращает строку типа "Круг радиусом 5").

**Задача 3 (посложнее, интерфейс с default-методом).** Интерфейс `Discountable`
с абстрактным `originalPrice(): Double` и default-методом
`discountedPrice(): Double = originalPrice() * 0.9` (скидка 10%). Класс
`Product(name, price)` реализует интерфейс. Проверь, что `discountedPrice()`
работает без переопределения.

### Решения

**Employee / Manager / Developer**
```kotlin
abstract class Employee(
    val name: String,
    val baseSalary: Double,
) {
    abstract fun calculateSalary(): Double

    fun printPaycheck() {
        println("Name: $name salary: ${calculateSalary()}")
    }
}

class Manager(
    name: String,
    baseSalary: Double,
    private val bonus: Double,
) : Employee(name, baseSalary) {
    override fun calculateSalary(): Double = baseSalary + bonus
}

class Developer(
    name: String,
    baseSalary: Double,
) : Employee(name, baseSalary) {
    override fun calculateSalary(): Double = baseSalary
}
```

**Drawable / CircleImpl / SquareImpl**
```kotlin
interface Drawable {
    fun draw(): String
}

class CircleImpl(private val radius: Int) : Drawable {
    override fun draw(): String = "Круг радиусом $radius"
}

class SquareImpl(private val side: Int) : Drawable {
    override fun draw(): String = "Квадрат со стороной в $side"
}
```

**Discountable / ProductImpl**
```kotlin
interface Discountable {
    abstract fun originalPrice(): Double

    fun discountedPrice(): Double = originalPrice() * 0.9
}

class ProductImpl(
    private val name: String,
    private val price: Double,
) : Discountable {
    override fun originalPrice(): Double = price
}
```

### Разбор

- **Employee/Manager/Developer** — без замечаний, чистый пример правильного
  `abstract class`: общая логика (`printPaycheck`) в родителе, специфика — в
  наследниках. Именно так это и задумано.
- **Drawable** — логика верная. Замечание по неймингу: суффикс `Impl`
  (`CircleImpl`, `SquareImpl`) обычно добавляют, когда есть конфликт имён —
  например, если бы уже был класс/интерфейс `Circle`, а это его конкретная
  реализация. Здесь конфликта нет (`Drawable` называется иначе), поэтому `Impl`
  избыточен — достаточно `Circle`, `Square`. Мелочь, но в реальном коде такие
  суффиксы "по привычке" засоряют читаемость; в текущем коде пока не
  переименовано.
- **Discountable** — работает верно. Тонкость: ключевое слово `abstract` в
  `interface Discountable { abstract fun originalPrice(): Double }` лишнее —
  в интерфейсе метод без тела автоматически абстрактный, писать `abstract`
  явно не нужно (в отличие от `abstract class`, где это обязательно, чтобы
  отличить абстрактный метод от метода с реализацией). Правило для памяти: в
  `interface` умолчание — "абстрактный, если нет тела", в `abstract class`
  умолчание — "обычный метод, `abstract` пиши явно".
- Похвала: аккуратная раскладка задач по пакетам (`lesson002.q1`, `q2`, `q3`) —
  хорошая привычка, так и в реальных проектах структурируют код по
  фичам/модулям.

---

## Lesson 003 — data class, sealed class, enum class, null safety 🔶

### Теория

**data class.** Когда класс — просто контейнер данных (DTO, модель), незачем
руками писать `equals`, `hashCode`, `toString`, `copy`:
```kotlin
data class User(val name: String, val age: Int)

fun main() {
    val u1 = User("Аня", 25)
    val u2 = User("Аня", 25)
    println(u1 == u2)          // true — сравнение по значению, не по ссылке
    println(u1)                 // User(name=Аня, age=25) — toString готов
    val u3 = u1.copy(age = 26)  // копия с изменённым полем
}
```
`==` в Kotlin — это всегда сравнение по значению (`equals`), а не по ссылке
(для ссылки есть `===`). У обычного класса `equals` по умолчанию сравнивает
ссылки, у `data class` — генерируется честное сравнение по полям.

**enum class**
```kotlin
enum class Status {
    ACTIVE, INACTIVE, BANNED
}
```
Можно и с параметрами:
```kotlin
enum class Status(val description: String) {
    ACTIVE("Активен"),
    INACTIVE("Неактивен"),
    BANNED("Заблокирован")
}
```

**sealed class.** Это как `enum`, но каждый вариант может нести разные данные и
быть отдельным классом. Используется, когда набор вариантов заранее известен и
закрыт (нельзя унаследоваться откуда-то ещё, кроме как в том же файле/модуле):
```kotlin
sealed class ApiResult
data class Success(val data: String) : ApiResult()
data class Error(val message: String) : ApiResult()
object Loading : ApiResult()
```
Главная фишка — `when` по sealed class компилятор проверяет на полноту:
```kotlin
fun handle(result: ApiResult) = when (result) {
    is Success -> println("Данные: ${result.data}")
    is Error -> println("Ошибка: ${result.message}")
    Loading -> println("Загрузка...")
    // если забудешь ветку — не скомпилируется, а не упадёт в рантайме
}
```
Это именно то, чем в Spring-приложениях часто моделируют результат операции
(успех/ошибка/etc) вместо кидания исключений на каждый чих.

**Null safety.** Ключевая особенность Kotlin. Тип `String` не может быть `null`.
Если может — пишешь `String?`:
```kotlin
var name: String = "Аня"
name = null  // ошибка компиляции

var nickname: String? = null  // ок
```
Работа с nullable-типами:
```kotlin
val nickname: String? = null

// safe call — если null, вся цепочка вернёт null
println(nickname?.length)

// elvis operator — значение по умолчанию, если null
val len = nickname?.length ?: 0

// !!  — "я уверен, что не null" (кинет NPE, если ошибся; использовать редко, осознанно)
val forcedLen = nickname!!.length
```
Правило: `!!` — это красный флаг в код-ревью. Если видишь его у себя часто —
значит, дизайн можно улучшить (например, сделать поле не-nullable или
обработать случай через `?:`).

### Задания

**Задача 1.** Создай `data class Point(x: Int, y: Int)`. Напиши функцию
`distance(p1: Point, p2: Point): Double`, считающую расстояние между точками.
Проверь в `main`, что `copy()` и `==` работают как ожидается.

**Задача 2.** Создай `sealed class PaymentResult` с тремя вариантами:
`Success(val transactionId: String)`, `Failure(val reason: String)`, `Pending`
(без данных). Напиши функцию `describe(result: PaymentResult): String`, которая
через `when` возвращает читаемое описание для каждого случая.

**Задача 3 (null safety).** Есть `data class Address(val city: String?, val zipCode: String?)`
и `data class Person(val name: String, val address: Address?)`. Напиши функцию
`getCityOrDefault(person: Person): String`, которая возвращает город человека, а
если адреса нет или города нет — возвращает `"Неизвестно"`. Сделай это через
safe call + elvis, без единого `!!`.

### Решения (текущее состояние)

**Point / Calculate.distance** 🔶 нужна правка
```kotlin
data class Point(
    val x: Int,
    val y: Int,
)

@Service
class Calculate {
    fun distance(p1: Point, p2: Point): Double {
        var x = 0.0
        var y = 0.0
        x = if (p1.x > p2.x) (p1.x - p2.x).toDouble() else (p2.x - p1.x).toDouble()
        y = if (p1.y > p2.y) (p1.y - p2.y).toDouble() else (p2.y - p1.y).toDouble()
        val sumSq = x * x + y * y
        return sqrt(sumSq)
    }
}
```
`copy()`/`==` проверены не отдельным `main()`, а прямо в `LearningApplication`
(`CommandLineRunner`, DI через `Calculate`):
```kotlin
val pointX = Point(10, 10)
val pointY = Point(1, 1)
if (pointY == pointX) { println("123123") }
val pointCopy = pointX.copy(y = 1)
println(pointCopy)
val distance = calculate.distance(pointX, pointY)
println(distance)
```
Это рабочий и вполне уместный вариант проверки — засчитано.

**PaymentResult / describe** 🔶 не закончена
```kotlin
sealed class PaymentResult
class Success(val transactionId: String) : PaymentResult()
class Failure(val reason: String) : PaymentResult()
object Pending : PaymentResult()
```
`Result.kt` пока пустая заглушка:
```kotlin
class Result() {}
```

**Address / Person / getCityOrDefault** ⬜ не начата.

### Разбор (по факту сделанного)

- **Point/Calculate** — сам `distance` считает верно, но написан избыточно:
  ручной if/else для получения модуля разницы не нужен — при возведении в
  квадрат (`x * x`) знак всё равно исчезает, поэтому можно сразу
  `val dx = (p1.x - p2.x).toDouble()` без ветвления. Ещё проще — использовать
  готовую `kotlin.math.hypot(dx, dy)`, которая как раз считает
  `sqrt(dx² + dy²)`. В файле остался закомментированный мёртвый код
  (`if (x == 0.0) return y...`) — такое стоит удалять, а не оставлять
  закомментированным: он и не нужен, формула и так корректно работает при
  x=0 или y=0.
- **PaymentResult** — `sealed class` сделан правильно (три варианта: класс с
  данными, класс с данными, `object` без данных — ровно то, что просят под
  `Success`/`Failure`/`Pending`). Осталось написать `describe()` в `Result.kt`
  через `when` по трём веткам — компилятор проверит полноту сам.
- **Null safety (задача 3)** — ещё не начата, будет хорошей проверкой на
  `?.`/`?:` без единого `!!`.

---

_Обновляется по мере прохождения. Общий план — в [`PLAN.md`](PLAN.md)._
