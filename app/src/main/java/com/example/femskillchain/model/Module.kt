package com.example.femskillchain.model

data class Module(
    val id: String,
    val title: String,
    val description: String,
    val category: ModuleCategory,
    val imageUrl: String,
    val duration: String,
    val level: String,
    val isEnrolled: Boolean = false
)

enum class ModuleCategory {
    TECHNICAL,
    SOFT_SKILLS,
    LEADERSHIP
} 