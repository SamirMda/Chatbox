package com.example.chatbox.viewModel

import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SignUpViewModelTest {
    @Test
    fun checkPassword_Short() {
        val password = "0As?"
        val result = SignUpViewModel().checkPassword("0As?")
        val length = password.length
        Assert.assertFalse("length = $length\n" +
                "result = $result", result)
    }

    @Test
    fun checkPassword_NoMin() {
        val password = "0A-PL3"
        val result = SignUpViewModel().checkPassword(password)
        val min = Regex(".*[a-z]+.*")

        Assert.assertFalse("min = ${min.matches(password)}\n" +
                "result = $result", result)
    }

    @Test
    fun checkPassword_NoMaj() {
        val password = "0!okds"
        val result = SignUpViewModel().checkPassword(password)
        val maj = Regex(".*[A-Z]+.*")

        Assert.assertFalse("maj = ${maj.matches(password)}\n" +
                "result = $result", result)
    }

    @Test
    fun checkPassword_NoNumber() {
        val password = "kl_pAs"
        val result = SignUpViewModel().checkPassword(password)
        val num = Regex(".*[0-9]+.*")

        Assert.assertFalse("num = ${num.matches(password)}\n" +
                "result = $result", result)
    }

    @Test
    fun checkPassword_NoCharSpec() {
        val password = "kBl4Sps"
        val result = SignUpViewModel().checkPassword(password)
        val specChar = Regex(".*[@\\+\\.\\-_\\?\\!\\$€]+.*")

        Assert.assertFalse("specChar = ${specChar.matches(password)}\n" +
        "result = $result", result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["kBl4+Sps", "kBl4.Sps", "kBl4\$Sps", "kBl4@Sps"])
    fun checkPassword_Good(passwords: String) {
        val result = SignUpViewModel().checkPassword(passwords)
        val min = Regex(".*[a-z]+.*")
        val maj = Regex(".*[A-Z]+.*")
        val num = Regex(".*[0-9]+.*")
        val specChar = Regex(".*[@+\\.\\-_\\?\\!\\$€]+.*")

        Assert.assertTrue("min = ${min.matches(passwords)}\n" +
                "maj = ${maj.matches(passwords)}\n" +
                "num = ${num.matches(passwords)}\n" +
                "specChar = ${specChar.matches(passwords)}\n" +
                "result = $result", result)
    }

    @Test
    fun checkEmail_NoAt() {
        val email = "test.com"
        val result = SignUpViewModel().checkEmail(email)

        Assert.assertFalse("result = $result", result)
    }

    @Test
    fun checkEmail_NoDot() {
        val email = "test@testcom"
        val result = SignUpViewModel().checkEmail(email)

        Assert.assertFalse("result = $result", result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["@test.com", "test@.com"])
    fun checkEmail_NoText(emails: String) {
        val result = SignUpViewModel().checkEmail(emails)

        Assert.assertFalse("result = $result", result)
    }

    @Test
    fun checkEmail_Good() {
        val email = "test@test.com"
        val result = SignUpViewModel().checkEmail(email)

        Assert.assertTrue("result = $result", result)
    }
}