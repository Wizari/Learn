package com.gmail.wizaripost.learning.balls_test
import com.gmail.wizaripost.learning.balls.calculateReelAnimationFlagsStatic
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertContentEquals

class ReelAnimationServiceTest {

    companion object {
        @JvmStatic
        fun provideTestCases(): List<TestCase> {
            return listOf(
                // Тест 1: Всего 5 шаров - ничего не анимируется
                TestCase(
                    matrix = arrayOf(
                        arrayOf(1, 0, 0), // рил 0
                        arrayOf(1, 0, 0), // рил 1
                        arrayOf(1, 0, 0), // рил 2
                        arrayOf(1, 0, 0), // рил 3
                        arrayOf(1, 0, 0)  // рил 4
                    ),
                    expected = booleanArrayOf(true, true, true, true, false),
                    description = "Total 5 balls - no animation"
                ),

                // Тест 2: Ровно 6 шаров - анимируются первые 3 рила
                TestCase(
                    matrix = arrayOf(
                        arrayOf(1, 1, 0), // 2 шара
                        arrayOf(1, 1, 0), // 2 шара
                        arrayOf(1, 1, 0), // 2 шара
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, true),
                    description = "Exactly 6 balls - first 3 reels animate"
                ),

                // Тест 3: 8 шаров, распределены неравномерно
                TestCase(
                    matrix = arrayOf(
                        arrayOf(1, 0, 0), // 1 шар
                        arrayOf(1, 1, 1), // 3 шара
                        arrayOf(1, 1, 0), // 2 шара
                        arrayOf(1, 0, 0), // 1 шар
                        arrayOf(1, 0, 0)  // 1 шар
                    ),
                    expected = booleanArrayOf(true, true, true, true, true),
                    description = "8 balls uneven distribution"
                ),

                // Тест 4: 10 шаров - анимируются все рилы
                TestCase(
                    matrix = arrayOf(
                        arrayOf(1, 1, 1), // 3 шара
                        arrayOf(1, 1, 1), // 3 шара
                        arrayOf(1, 1, 0), // 2 шара
                        arrayOf(1, 0, 0), // 1 шар
                        arrayOf(1, 0, 0)  // 1 шар
                    ),
                    expected = booleanArrayOf(true, true, true, true, true),
                    description = "10 balls - all reels animate"
                ),

                // Тест 5: 6 шаров в последних рилах
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(1, 1, 1), // 3 шара
                        arrayOf(1, 1, 1), // 3 шара
                        arrayOf(0, 0, 0)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, true),
                    description = "6 balls in last reels"
                ),

                // Тест 6: 7 шаров, но только последние 3 рила имеют шары
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(1, 1, 1), // 3 шара
                        arrayOf(1, 1, 1), // 3 шара
                        arrayOf(1, 0, 0)  // 1 шар
                    ),
                    expected = booleanArrayOf(true, true, true, true, true),
                    description = "7 balls in last 3 reels"
                ),

                // Тест 7: Пограничный случай - 6 шаров в одном риле
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(1, 1, 1), // 6 шаров (разная высота)
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, false),
                    description = "6 balls in single reel"
                ),

                // Тест 8: Нет шанса после 2-го рила
                TestCase(
                    matrix = arrayOf(
                        arrayOf(1, 1, 1), // 3 шара
                        arrayOf(1, 1, 0), // 2 шара
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, false),
                    description = "No chance after 2nd reel"
                ),
                // Тест 9:
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(1, 1, 1), // 6 шаров
                        arrayOf(0, 1, 0)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, false),
                    description = "6 balls in single reel"
                ),
                // Тест 10:
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0), // 0 шаров
                        arrayOf(1, 0, 0, 1), // 6 шаров
                        arrayOf(0, 1, 0, 0)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, false),
                    description = "6 balls in single reel"
                ),
                // Тест 11:
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(1, 0, 1), // 6 шаров
                        arrayOf(0, 1, 0)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, false, false),
                    description = "6 balls in single reel"
                ),
                // Тест 12:
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0, 1), // 6 шаров
                        arrayOf(1, 1, 1, 1, 1)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, true),
                    description = "6 balls in single reel"
                ),
                // Тест 13:
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0, 0, 0), // 6 шаров
                        arrayOf(1, 1, 1, 1, 1, 1)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, true),
                    description = "6 balls in single reel"
                ),
                // Тест 14:
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0, 0, 0, 0), // 6 шаров
                        arrayOf(0, 1, 1, 1, 1, 1)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, false),
                    description = "6 balls in single reel"
                ),
                //15
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(1, 1, 1), // 6 шаров
                        arrayOf(0, 1, 0)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, false),
                    description = "6 balls in single reel"
                ),
                //16
                TestCase(
                    matrix = arrayOf(
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(0, 0, 0), // 0 шаров
                        arrayOf(1, 1, 1), // 6 шаров
                        arrayOf(1, 1, 0)  // 0 шаров
                    ),
                    expected = booleanArrayOf(true, true, true, true, false),
                    description = "6 balls in single reel"
                ),
            )
        }
    }

    data class TestCase(
        val matrix: Array<Array<Int>>,
        val expected: BooleanArray,
        val description: String
    )

    @ParameterizedTest
    @MethodSource("provideTestCases")
    fun `test static implementation`(testCase: TestCase) {
        val result = calculateReelAnimationFlagsStatic(testCase.matrix)
        assertContentEquals(testCase.expected, result, testCase.description)
    }

    @Test
    fun `test edge case - empty matrix`() {
        val matrix = arrayOf(
            arrayOf<Int>(),
            arrayOf<Int>(),
            arrayOf<Int>(),
            arrayOf<Int>(),
            arrayOf<Int>()
        )

        val expected = booleanArrayOf(true, true, true, false, false)

        val resultStatic = calculateReelAnimationFlagsStatic(matrix)

        assertContentEquals(expected, resultStatic, "Empty matrix - static")

    }

    @Test
    fun `test performance with large matrix`() {
        // Создаем большую матрицу для теста производительности
        val largeMatrix = Array(5) { reel ->
            Array(100) { row ->
                if ((reel + row) % 3 == 0) 1 else 0
            }
        }

        // Просто вызываем методы чтобы проверить, что они не падают
        calculateReelAnimationFlagsStatic(largeMatrix)
        // Тест прошел успешно если не было исключений
        assertTrue(true, "Large matrix test passed without exceptions")
    }
}