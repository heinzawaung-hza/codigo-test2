package com.example.codigotest2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch
import javax.inject.Inject

data class FormState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val nationality: String = "",
    val country: String = "",
    val mobileNumber: String = ""
)


@HiltViewModel
class CreateAccountViewModel @Inject constructor() : ViewModel() {

    val _formState = MutableStateFlow(FormState())
    val formState: StateFlow<FormState> get() = _formState

    private val _firstNameError = MutableStateFlow<String?>(null)
    val firstNameError: StateFlow<String?> get() = _firstNameError

    private val _lastNameError = MutableStateFlow<String?>(null)
    val lastNameError: StateFlow<String?> get() = _lastNameError

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> get() = _emailError

    private val _nationalityError = MutableStateFlow<String?>(null)
    val nationalityError: StateFlow<String?> get() = _nationalityError

    private val _countryError = MutableStateFlow<String?>(null)
    val countryError: StateFlow<String?> get() = _countryError

    private val _mobileNumberError = MutableStateFlow<String?>(null)
    val mobileNumberError: StateFlow<String?> get() = _mobileNumberError

    // Function to validate First Name
    fun validateFirstName(firstName: String) {
        _firstNameError.value = if (firstName.isBlank()) "First name cannot be empty." else null
    }

    // Function to validate Last Name
    fun validateLastName(lastName: String) {
        _lastNameError.value = if (lastName.isBlank()) "Last name cannot be empty." else null
    }

    fun validateEmail(email: String) {
        _emailError.value = if (email.isBlank()) "Email cannot be empty." else null
    }

    fun validateNationality(nationality: String) {
        _nationalityError.value =
            if (nationality.isBlank()) "Nationality cannot be empty." else null
    }

    fun validateCountry(country: String) {
        _countryError.value =
            if (country.isBlank()) "Country of residence cannot be empty." else null
    }

    fun submitForm(
        firstName: String,
        lastName: String,
        email: String,
        nationality: String,
        country: String,
        mobileNumber: String
    ) {
        if (firstName.isBlank()) {
            _firstNameError.value = "First name cannot be empty."
        } else {
            _firstNameError.value = null
        }

        if (lastName.isBlank()) {
            _lastNameError.value = "Last name cannot be empty."
        } else {
            _lastNameError.value = null
        }

        if (email.isBlank()) {
            _emailError.value = "Email cannot be empty."
        } else {
            _emailError.value = null
        }

        if (nationality.isBlank()) {
            _nationalityError.value = "Nationality cannot be empty."
        } else {
            _nationalityError.value = null
        }

        if (country.isBlank()) {
            _countryError.value = "Country of residence cannot be empty."
        } else {
            _countryError.value = null
        }

        if (
            firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            email.isNotBlank() &&
            nationality.isNotBlank() &&
            country.isNotBlank()

        ) {
            viewModelScope.launch {

            }
        }
    }
}