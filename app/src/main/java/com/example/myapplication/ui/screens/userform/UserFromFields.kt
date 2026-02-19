package com.example.myapplication.ui.screens.userform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.FormTextField

data class FormFieldsState (
    val lastName: String,
    val firstName: String,
    val patronymic: String,
    val birthDate: String,
    val submitted: Boolean
) {
    val isValid: Boolean
        get() = lastName.isNotBlank() &&
                firstName.isNotBlank() &&
                patronymic.isNotBlank() &&
                birthDate.isNotBlank()
}


data class FormFieldsCallbacks(
    val onLastNameChange: (String) -> Unit,
    val onFirstNameChange: (String) -> Unit,
    val onPatronymicChange: (String) -> Unit,
    val onBirthDateChange: (String) -> Unit
)

@Composable
fun UserFormFields(
    state: FormFieldsState,
    callbacks: FormFieldsCallbacks
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Личные данные", fontSize = 20.sp, color = Color.Black)
        Text("Заполните все поля для продолжения", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        FormTextField(
            value = state.lastName,
            onValueChange = callbacks.onLastNameChange,
            label = "Фамилия",
            isError = state.submitted && state.lastName.isBlank()
        )

        FormTextField(
            value = state.firstName,
            onValueChange = callbacks.onFirstNameChange,
            label = "Имя",
            isError = state.submitted && state.firstName.isBlank()
        )

        FormTextField(
            value = state.patronymic,
            onValueChange = callbacks.onPatronymicChange,
            label = "Отчество",
            isError = state.submitted && state.patronymic.isBlank()
        )

        FormTextField(
            value =  state.birthDate,
            onValueChange = callbacks.onBirthDateChange,
            label = "Дата рождения",
            placeholder = "ДД.ММ.ГГГГ",
            isError = state.submitted && state.birthDate.isBlank()
        )

    }
}
