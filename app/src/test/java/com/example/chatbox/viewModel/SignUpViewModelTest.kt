package com.example.chatbox.viewModel

import org.junit.Assert
import org.junit.Test

class SignUpViewModelTest {
    @Test
    fun checkShortPassword() {
        val password = "0As?"
        val result = SignUpViewModel().checkPassword("0As?")
        val length = password.length
        Assert.assertFalse("length = $length\n" +
                "result = $result", result)
    }

    @Test
    fun checkNoMin() {
        val password = "0A-PL3"
        val result = SignUpViewModel().checkPassword(password)
        val min = Regex(".*[a-z]+.*")

        Assert.assertFalse("min = ${min.matches(password)}\n" +
                "result = $result", result)
    }

    @Test
    fun checkNoMaj() {
        val password = "0!okds"
        val result = SignUpViewModel().checkPassword(password)
        val maj = Regex(".*[A-Z]+.*")

        Assert.assertFalse("maj = ${maj.matches(password)}\n" +
                "result = $result", result)
    }

    @Test
    fun checkNoNumberPassword() {
        val password = "kl_pAs"
        val result = SignUpViewModel().checkPassword(password)
        val num = Regex(".*[0-9]+.*")

        Assert.assertFalse("num = ${num.matches(password)}\n" +
                "result = $result", result)
    }

    @Test
    fun checkNoCharSpecPassword() {
        val password = "kBl4Sps"
        val result = SignUpViewModel().checkPassword(password)
        val specChar = Regex(".*[@\\+\\.\\-_\\?\\!\\$€]+.*")

        Assert.assertFalse("specChar = ${specChar.matches(password)}\n" +
        "result = $result", result)
    }

    @Test
    fun checkGoodPassword() {
        val password = "kBl4+Sps"
        val result = SignUpViewModel().checkPassword(password)
        val min = Regex(".*[a-z]+.*")
        val maj = Regex(".*[A-Z]+.*")
        val num = Regex(".*[0-9]+.*")
        val specChar = Regex(".*[@+\\.\\-_\\?\\!\\$€]+.*")

        Assert.assertTrue("min = ${min.matches(password)}\n" +
                "maj = ${maj.matches(password)}\n" +
                "num = ${num.matches(password)}\n" +
                "specChar = ${specChar.matches(password)}\n" +
                "result = $result", result)
    }
}