package com.example.myapplication.ui.screens.userform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.UserFormButtons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen() {
    var lastName by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val callbacks = FormFieldsCallbacks(onLastNameChange = {
        lastName = it
        if (submitted) submitted = false
    }, onFirstNameChange = {
        firstName = it
        if (submitted) submitted = false
    }, onPatronymicChange = {
        patronymic = it
        if (submitted) submitted = false
    }, onBirthDateChange = {
        birthDate = it
        if (submitted) submitted = false
    })


    Scaffold(
        topBar = {
            UserFormTopBar() }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserFormFields(
                state = FormFieldsState(
                    lastName, firstName, patronymic, birthDate, submitted
                ), callbacks = callbacks
            )

            Spacer(modifier = Modifier.weight(1f))

            UserFormButtons(onSave = { submitted = true }, onShowInformation = {
                val state = FormFieldsState(lastName, firstName, patronymic, birthDate, submitted)
                if (state.isValid) {
                    showDialog = true
                } else {
                    submitted = true
                }
            }, onClear = {
                lastName = ""; firstName = "";
                patronymic = ""; birthDate = "";
                submitted = false
            })
        }
    }

    if (showDialog) {
        UserInformationDialog(
            lastName, firstName, patronymic, birthDate, onDismiss = { showDialog = false })
    }

}