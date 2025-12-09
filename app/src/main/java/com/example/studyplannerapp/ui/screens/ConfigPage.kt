package com.example.studyplannerapp.ui.screens

// 1. ADD THIS IMPORT
import androidx.compose.foundation.border

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Import your theme colors
import com.example.studyplannerapp.ui.theme.*

// --- Local Colors for this Screen ---
val GithubBlue = Color(0xFF2F81F7)
val GithubPurple = Color(0xFFA371F7)
val GithubGreen = Color(0xFF238636)
val GithubRed = Color(0xFFDA3633)
val AvatarBlue = Color(0xFF4285F4)

@Composable
fun SettingsScreen() {
    // 1. Theme Detection
    val isDark = isSystemInDarkTheme()

    // 2. Manual Color Resolution
    val borderColor = if (isDark) DarkBorder else LightBorder
    val mutedText = if (isDark) DarkMutedForeground else LightMutedForeground
    val cardBg = MaterialTheme.colorScheme.surface
    val mainText = MaterialTheme.colorScheme.onSurface

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = mainText
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- Section 1: User Profile ---
            ProfileHeader(borderColor, mutedText, cardBg, mainText)

            // --- Section 2: Stats ---
            StatsSection(borderColor, mutedText, cardBg, mainText)

            // --- Section 3: Appearance ---
            AppearanceSection(borderColor, mutedText, cardBg, mainText, isDark)

            // --- Section 4: Data Management ---
            DataManagementSection(borderColor, mutedText, cardBg, mainText)
        }
    }
}

// ---------------- SUB-COMPOSABLES ----------------

@Composable
fun ProfileHeader(borderColor: Color, mutedText: Color, cardBg: Color, mainText: Color) {
    Card(
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(AvatarBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Text("DU", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.width(16.dp))

                // User Details
                Column {
                    Text("Demo User", fontWeight = FontWeight.SemiBold, color = mainText)
                    Text("demo@gmail.com", fontSize = 12.sp, color = mutedText)
                }
            }

            // Sign Out Button
            OutlinedButton(
                onClick = {},
                border = BorderStroke(1.dp, borderColor),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = mainText)
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Sign out", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun StatsSection(borderColor: Color, mutedText: Color, cardBg: Color, mainText: Color) {
    Card(
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(">_", fontWeight = FontWeight.Bold, color = mutedText)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Repository Stats", fontWeight = FontWeight.SemiBold, color = mainText)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatBox(modifier = Modifier.weight(1f), "0", "Issues", GithubBlue, borderColor, cardBg, mutedText)
                StatBox(modifier = Modifier.weight(1f), "0", "Resolved", GithubGreen, borderColor, cardBg, mutedText)
                StatBox(modifier = Modifier.weight(1f), "0", "Minutes", GithubPurple, borderColor, cardBg, mutedText)
            }
        }
    }
}

@Composable
fun StatBox(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    accentColor: Color,
    borderColor: Color,
    cardBg: Color,
    mutedText: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .border(1.dp, borderColor, RoundedCornerShape(6.dp)) // This now works
            .background(cardBg)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = accentColor)
            Text(label, fontSize = 12.sp, color = mutedText)
        }
    }
}

@Composable
fun AppearanceSection(borderColor: Color, mutedText: Color, cardBg: Color, mainText: Color, isDark: Boolean) {
    Column {
        Text("Appearance", fontWeight = FontWeight.Medium, color = mainText)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = cardBg),
            border = BorderStroke(1.dp, borderColor),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ThemeButton(
                    modifier = Modifier.weight(1f),
                    text = "Light",
                    isSelected = !isDark,
                    icon = Icons.Default.ExitToApp,
                    borderColor = borderColor,
                    mainText = mainText
                )

                ThemeButton(
                    modifier = Modifier.weight(1f),
                    text = "Dark",
                    isSelected = isDark,
                    icon = Icons.Default.ExitToApp,
                    borderColor = borderColor,
                    mainText = mainText
                )
            }
        }
    }
}

@Composable
fun ThemeButton(
    modifier: Modifier,
    text: String,
    isSelected: Boolean,
    icon: ImageVector,
    borderColor: Color,
    mainText: Color
) {
    val bgColor = if (isSelected) GithubGreen else Color.Transparent
    val contentColor = if (isSelected) Color.White else mainText
    val border = if (isSelected) BorderStroke(0.dp, Color.Transparent) else BorderStroke(1.dp, borderColor)

    Button(
        onClick = { /* TODO: Toggle Theme */ },
        modifier = modifier.height(40.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = contentColor
        ),
        border = border,
        contentPadding = PaddingValues(0.dp)
    ) {
        // You can add Icon here
        Text(text)
    }
}

@Composable
fun DataManagementSection(borderColor: Color, mutedText: Color, cardBg: Color, mainText: Color) {
    Column {
        Text("Data Management", fontWeight = FontWeight.Medium, color = mainText)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = cardBg),
            border = BorderStroke(1.dp, borderColor),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Export Item
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                        .border(1.dp, borderColor, RoundedCornerShape(6.dp)) // This now works
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.KeyboardArrowRight, contentDescription = null, tint = mainText)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Export Data", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = mainText)
                        Text("Download backup as JSON", fontSize = 12.sp, color = mutedText)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Delete Item
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                        .border(1.dp, GithubRed, RoundedCornerShape(6.dp)) // This now works
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Delete, contentDescription = null, tint = mainText)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Clear All Data", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = mainText)
                        Text("Permanently delete all tasks", fontSize = 12.sp, color = mutedText)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreviewLight() {
    MaterialTheme {
        SettingsScreen()
    }
}