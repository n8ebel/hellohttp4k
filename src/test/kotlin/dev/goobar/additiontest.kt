package dev.goobar

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.random.Random

class AdditionTest {

    private fun randomNatural() = Random.nextInt(from = 0, until = 1_000_000)

    @Test
    fun `add 2 numbers`() {
        expectThat(5 + 6).isEqualTo(11)
        expectThat(5 - 6).isEqualTo(-1)
    }

    @Test
    fun `zero identity`() {
        repeat(100) {
            val random = randomNatural()
            expectThat(0 + random).isEqualTo(random)
        }
    }

    @Test
    fun `commutative property`() {
        repeat(100) {
            val random1 = randomNatural()
            val random2 = randomNatural()

            expectThat(random1 + random2).isEqualTo(random2 + random1)
        }
    }
}