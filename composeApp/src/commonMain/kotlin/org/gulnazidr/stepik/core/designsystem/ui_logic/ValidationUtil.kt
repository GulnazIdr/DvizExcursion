package org.gulnazidr.stepik.core.designsystem.ui_logic

import org.gulnazidr.stepik.feature.auth.domain.result.LocalError

object ValidationUtil {
    fun validatePassword(password: String?): LocalError.PasswordError?{
        if (password.isNullOrBlank()) return LocalError.PasswordError.EMPTY_FIELD

        val noLetter= !password.any{it.isLetter()}
        val noDigit = !password.any{it.isDigit()}
        val noUpper = !password.any{it.isUpperCase()}
        val notEnoughChars = password.length < 8

        val regexSymbol = Regex(".*[!@#$%^&*()+=?~`,./{}].*")
        val noSymbol = !password.matches(regexSymbol)

        val errorMessage = when{
            noLetter -> LocalError.PasswordError.NO_LETTERS
            noDigit -> LocalError.PasswordError.NO_DIGITS
            noUpper -> LocalError.PasswordError.NO_UPPERCASE
            noSymbol -> LocalError.PasswordError.NO_SYMBOLS
            notEnoughChars -> LocalError.PasswordError.NOT_ENOUGH_CHARACTERS
            else -> null
        }

        if(notEnoughChars || noUpper || noLetter || noDigit || noSymbol)
            return errorMessage

        return null
    }

    fun validateEmail(email: String?): LocalError.FieldError?{
        if (email.isNullOrBlank()) return LocalError.FieldError.EMPTY_FIELD

        val isCorrect = Regex(
            "^[a-z0-9._]+@[a-z]+\\.[a-z]{2,}\$"
        ).matches(email)

        return if (isCorrect) null
        else LocalError.FieldError.WRONG_FORMAT
    }

    fun validateName(name: String?): LocalError.FieldError?{
        return if (name.isNullOrBlank()) return LocalError.FieldError.EMPTY_FIELD
        else null
    }

    fun validatePolicy(isChecked: Boolean): LocalError.FieldError?{
        return if (isChecked) null
        else LocalError.FieldError.POLICY_UNCHECKED
    }

    fun validatePhone(phone: String?): LocalError.FieldError?{
        return if (phone.isNullOrBlank()) return LocalError.FieldError.WRONG_FORMAT
        else null
    }
}