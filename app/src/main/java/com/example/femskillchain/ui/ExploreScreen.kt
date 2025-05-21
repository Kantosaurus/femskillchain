package com.example.femskillchain.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.femskillchain.model.Module
import com.example.femskillchain.model.ModuleCategory

@Composable
fun ExploreScreen(
    modules: List<Module> = sampleModules(),
    onEnroll: (Module) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ModuleCategory?>(null) }
    var enrolledModules by remember { mutableStateOf(setOf<String>()) }

    val filteredModules = modules.filter { module ->
        val matchesQuery = module.title.contains(searchQuery, ignoreCase = true) ||
                module.description.contains(searchQuery, ignoreCase = true)
        val matchesCategory = selectedCategory == null || module.category == selectedCategory
        matchesQuery && matchesCategory
    }.map { module ->
        if (enrolledModules.contains(module.id)) module.copy(isEnrolled = true) else module
    }

    Column(modifier = modifier.fillMaxSize().background(Color(0xFFF8EBEF))) {
        Text(
            text = "Explore Courses",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(24.dp, 24.dp, 24.dp, 8.dp)
        )
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search modules...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            singleLine = true
        )
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = selectedCategory == null,
                onClick = { selectedCategory = null },
                label = { Text("All") }
            )
            FilterChip(
                selected = selectedCategory == ModuleCategory.TECHNICAL,
                onClick = { selectedCategory = ModuleCategory.TECHNICAL },
                label = { Text("Technical") }
            )
            FilterChip(
                selected = selectedCategory == ModuleCategory.SOFT_SKILLS,
                onClick = { selectedCategory = ModuleCategory.SOFT_SKILLS },
                label = { Text("Soft Skills") }
            )
            FilterChip(
                selected = selectedCategory == ModuleCategory.LEADERSHIP,
                onClick = { selectedCategory = ModuleCategory.LEADERSHIP },
                label = { Text("Leadership") }
            )
        }
        Spacer(Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredModules) { module ->
                ModuleCard(
                    module = module,
                    onEnroll = {
                        enrolledModules = enrolledModules + module.id
                        onEnroll(module)
                    }
                )
            }
        }
    }
}

@Composable
fun ModuleCard(module: Module, onEnroll: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = module.imageUrl,
                contentDescription = module.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
            Column(Modifier.padding(16.dp)) {
                Text(module.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(
                    module.description,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AssistChip(
                        onClick = {},
                        label = { Text(module.category.name.replace("_", " ")) }
                    )
                    Spacer(Modifier.weight(1f))
                    Button(
                        onClick = onEnroll,
                        enabled = !module.isEnrolled
                    ) {
                        Text(if (module.isEnrolled) "Enrolled" else "Enroll")
                    }
                }
            }
        }
    }
}

fun sampleModules() = listOf(
    Module(
        id = "1",
        title = "Introduction to Android Development",
        description = "Learn the basics of Android app development with Kotlin",
        category = ModuleCategory.TECHNICAL,
        imageUrl = "https://images.unsplash.com/photo-1519389950473-47ba0277781c",
        duration = "4 weeks",
        level = "Beginner"
    ),
    Module(
        id = "2",
        title = "Effective Communication",
        description = "Master the art of professional communication",
        category = ModuleCategory.SOFT_SKILLS,
        imageUrl = "https://images.unsplash.com/photo-1503676382389-4809596d5290",
        duration = "2 weeks",
        level = "Intermediate"
    ),
    Module(
        id = "3",
        title = "Team Leadership",
        description = "Develop essential leadership skills for tech teams",
        category = ModuleCategory.LEADERSHIP,
        imageUrl = "https://images.unsplash.com/photo-1461749280684-dccba630e2f6",
        duration = "6 weeks",
        level = "Advanced"
    )
) 