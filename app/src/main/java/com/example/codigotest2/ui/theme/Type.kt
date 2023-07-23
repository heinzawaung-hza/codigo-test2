package com.example.codigotest2.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.codigotest2.R

// Set of Material typography styles to start with
val chivoFontFamily = FontFamily(

    Font(R.font.chivo_regular),
    Font(R.font.chivo_light, FontWeight.Light),
    Font(R.font.chivo_italic, style = FontStyle.Italic),
    Font(R.font.chivo_bold, FontWeight.Bold),

)


private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = chivoFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = chivoFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = chivoFontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = chivoFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = chivoFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = chivoFontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = chivoFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = chivoFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = chivoFontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = chivoFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = chivoFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = chivoFontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = chivoFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = chivoFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = chivoFontFamily)
)