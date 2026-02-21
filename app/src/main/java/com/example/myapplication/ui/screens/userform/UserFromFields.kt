package com.example.myapplication.ui.screens.userform

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.FormTextField


data class FormFieldsState(
    val lastName: String,
    val firstName: String,
    val patronymic: String,
    val birthDate: String,
    val submitted: Boolean
) {
    val isValid: Boolean
        get() = lastName.isNotBlank() && firstName.isNotBlank() &&
                patronymic.isNotBlank() && birthDate.isNotBlank()
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
    callbacks: FormFieldsCallbacks,
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
) {


    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Личные данные", fontSize = 20.sp, color = Color.Black)
        Text("Заполните все поля для продолжения", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        FormTextField(
            value = state.lastName,
            onValueChange = callbacks.onLastNameChange,
            label = "Фамилия",
            isError = state.submitted && state.lastName.isBlank(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        FormTextField(
            value = state.firstName,
            onValueChange = callbacks.onFirstNameChange,
            label = "Имя",
            isError = state.submitted && state.firstName.isBlank(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        FormTextField(
            value = state.patronymic,
            onValueChange = callbacks.onPatronymicChange,
            label = "Отчество",
            isError = state.submitted && state.patronymic.isBlank(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        FormTextField(
            value = state.birthDate,
            onValueChange = callbacks.onBirthDateChange,
            label = "Дата рождения",
            placeholder = "ДД.ММ.ГГГГ",
            isError = state.submitted && state.birthDate.isBlank(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )

    }
}
