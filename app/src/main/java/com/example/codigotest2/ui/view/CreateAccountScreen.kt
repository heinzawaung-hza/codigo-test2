package com.example.codigotest2.ui.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.codigotest2.viewmodel.CreateAccountViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(
    navController: NavController, viewModel: CreateAccountViewModel = hiltViewModel()
) {

    val formState by viewModel.formState.collectAsState()
    val firstNameError by viewModel.firstNameError.collectAsState()
    val lastNameError by viewModel.lastNameError.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val nationalityError by viewModel.nationalityError.collectAsState()
    val countryError by viewModel.countryError.collectAsState()
    val mobileNumberError by viewModel.mobileNumberError.collectAsState()


    Scaffold(topBar = {
        TopAppBar(title = {  }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        })
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = "Almost there!")
            Text(text = "Complete the form below to create your Ready To Travel account")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "*Mandatory")

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "First Name*")
            TextField(
                value = formState.firstName,
                onValueChange = { newFirstName ->
                    viewModel.validateFirstName(newFirstName)
                    viewModel._formState.value = formState.copy(firstName = newFirstName)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                singleLine = true,
                isError = firstNameError != null,
                visualTransformation = if (firstNameError != null) VisualTransformation.None else VisualTransformation.None
            )
            firstNameError?.let { error ->
                Text(
                    text = error, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Text(text = "Last Name*")
            TextField(
                value = formState.lastName,
                onValueChange = { newLastName ->
                    viewModel.validateLastName(newLastName)
                    viewModel._formState.value = formState.copy(lastName = newLastName)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                singleLine = true,
                isError = lastNameError != null
            )
            lastNameError?.let { error ->
                Text(
                    text = error, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Text(text = "Email Address*")
            TextField(
                value = formState.email,
                onValueChange = { newEmail ->
                    viewModel.validateEmail(newEmail)
                    viewModel._formState.value = formState.copy(email = newEmail)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                singleLine = true,
                isError = emailError != null,
                visualTransformation = if (emailError != null) VisualTransformation.None else VisualTransformation.None
            )
            emailError?.let { error ->
                Text(
                    text = error, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp)
                )
            }


            Text(text = "Nationality*")
            TextField(
                value = formState.nationality,
                onValueChange = { newNationality ->
                    viewModel.validateNationality(newNationality)
                    viewModel._formState.value = formState.copy(nationality = newNationality)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                singleLine = true,
                isError = nationalityError != null,
                visualTransformation = if (nationalityError != null) VisualTransformation.None else VisualTransformation.None
            )
            nationalityError?.let { error ->
                Text(
                    text = error, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Text(text = "Country of Residence*")
            TextField(
                value = formState.country,
                onValueChange = { newCountry ->
                    viewModel.validateCountry(newCountry)
                    viewModel._formState.value = formState.copy(country = newCountry)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                singleLine = true,
                isError = countryError != null,
                visualTransformation = if (countryError != null) VisualTransformation.None else VisualTransformation.None
            )
            countryError?.let { error ->
                Text(
                    text = error, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp)
                )
            }


            Text(text = "Mobile no. (Optional)")
            TextField(
                value = formState.mobileNumber,
                onValueChange = { newMobileNumber ->
                    viewModel._formState.value = formState.copy(mobileNumber = newMobileNumber)
                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),

                singleLine = true,
                isError = mobileNumberError != null,
                visualTransformation = if (mobileNumberError != null) VisualTransformation.None else VisualTransformation.None
            )
            mobileNumberError?.let { error ->
                Text(
                    text = error, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp)
                )
            }


            Button(
                onClick = {
                    viewModel.submitForm(
                        formState.firstName,
                        formState.lastName,
                        formState.email,
                        formState.nationality,
                        formState.country,
                        formState.mobileNumber
                    )
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Create my account now")
            }


        }
    })
}

