@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.femskillchain.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets

@Composable
fun MarketplaceScreen(navController: NavController, paddingValues: PaddingValues) {
    var isEmployer by remember { mutableStateOf(false) }
    var selectedJob by remember { mutableStateOf<Job?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        // Toggle switch for Employer/Employee view
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isEmployer) "Employer Dashboard" else "Employee Dashboard",
                style = MaterialTheme.typography.titleLarge
            )
            Switch(
                checked = isEmployer,
                onCheckedChange = { isEmployer = it }
            )
        }

        // Content based on selected view
        if (isEmployer) {
            EmployerDashboard()
        } else {
            EmployeeDashboard(navController)
        }
    }
}

// Add a data class for Job

data class Job(
    val title: String,
    val company: String,
    val skills: List<String>,
    val location: String,
    val description: String = "This is a sample job description."
)

@Composable
fun EmployeeDashboard(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val jobs = List(5) { index ->
        Job(
            title = "Sample Job ${index + 1}",
            company = "Company ${index + 1}",
            skills = listOf("Skill 1", "Skill 2", "Skill 3"),
            location = "Location ${index + 1}",
            description = "This is a description for Sample Job ${index + 1}."
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = { Text("Search jobs by skills, location...") },
            singleLine = true
        )

        // Job listings will go here
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(jobs) { job ->
                JobCard(
                    job = job,
                    onClick = { navController.navigate("jobDetail/${job.title}") }
                )
            }
        }
    }
}

@Composable
fun EmployerDashboard() {
    var searchQuery by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        // Search bar for workers
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = { Text("Search workers by skills, badges...") },
            singleLine = true
        )

        // Worker profiles will go here
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Placeholder for worker profiles
            items(List(5) { it }) { index ->
                WorkerProfileCard(
                    name = "Worker ${index + 1}",
                    skills = listOf("Skill 1", "Skill 2", "Skill 3"),
                    badges = listOf("Badge 1", "Badge 2"),
                    endorsements = index + 1
                )
            }
        }
    }
}

@Composable
fun JobCard(job: Job, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = job.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = job.company,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                job.skills.forEach { skill ->
                    AssistChip(
                        onClick = { },
                        label = { Text(skill) }
                    )
                }
            }
            Text(
                text = job.location,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun WorkerProfileCard(
    name: String,
    skills: List<String>,
    badges: List<String>,
    endorsements: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                skills.forEach { skill ->
                    AssistChip(
                        onClick = { },
                        label = { Text(skill) }
                    )
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                badges.forEach { badge ->
                    FilterChip(
                        selected = false,
                        onClick = { },
                        label = { Text(badge) }
                    )
                }
            }
            Text(
                text = "$endorsements endorsements",
                style = MaterialTheme.typography.bodySmall
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { }) {
                    Text("Assign Task")
                }
                Button(onClick = { }) {
                    Text("Endorse")
                }
            }
        }
    }
}

@Composable
fun JobDetailScreen(jobTitle: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = jobTitle,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "This is a placeholder for the job description and details.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
} 