package com.example.femskillchain.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.femskillchain.R
import com.example.femskillchain.adapter.ModuleAdapter
import com.example.femskillchain.databinding.ActivityExploreBinding
import com.example.femskillchain.model.Module
import com.example.femskillchain.model.ModuleCategory
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class ExploreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExploreBinding
    private lateinit var moduleAdapter: ModuleAdapter
    private var allModules = listOf<Module>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupSearch()
        setupFilters()
        loadModules()
        setupBottomNavigation()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupRecyclerView() {
        moduleAdapter = ModuleAdapter(emptyList()) { module ->
            handleEnrollClick(module)
        }
        
        binding.modulesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ExploreActivity)
            adapter = moduleAdapter
        }
    }

    private fun setupSearch() {
        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterModules(s?.toString() ?: "")
            }
        })
    }

    private fun setupFilters() {
        binding.filterChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedChip = group.findViewById<Chip>(checkedId)
            val category = when (selectedChip?.text) {
                "Technical" -> ModuleCategory.TECHNICAL
                "Soft Skills" -> ModuleCategory.SOFT_SKILLS
                "Leadership" -> ModuleCategory.LEADERSHIP
                else -> null
            }
            filterModules(binding.searchInput.text.toString(), category)
        }
    }

    private fun loadModules() {
        // TODO: Replace with actual API call
        allModules = listOf(
            Module(
                id = "1",
                title = "Introduction to Android Development",
                description = "Learn the basics of Android app development with Kotlin",
                category = ModuleCategory.TECHNICAL,
                imageUrl = "https://example.com/android.jpg",
                duration = "4 weeks",
                level = "Beginner"
            ),
            Module(
                id = "2",
                title = "Effective Communication",
                description = "Master the art of professional communication",
                category = ModuleCategory.SOFT_SKILLS,
                imageUrl = "https://example.com/communication.jpg",
                duration = "2 weeks",
                level = "Intermediate"
            ),
            Module(
                id = "3",
                title = "Team Leadership",
                description = "Develop essential leadership skills for tech teams",
                category = ModuleCategory.LEADERSHIP,
                imageUrl = "https://example.com/leadership.jpg",
                duration = "6 weeks",
                level = "Advanced"
            )
        )
        moduleAdapter.updateModules(allModules)
    }

    private fun filterModules(query: String, category: ModuleCategory? = null) {
        val filteredModules = allModules.filter { module ->
            val matchesQuery = module.title.contains(query, ignoreCase = true) ||
                    module.description.contains(query, ignoreCase = true)
            val matchesCategory = category == null || module.category == category
            matchesQuery && matchesCategory
        }
        moduleAdapter.updateModules(filteredModules)
    }

    private fun handleEnrollClick(module: Module) {
        // TODO: Implement actual enrollment logic
        Snackbar.make(
            binding.root,
            "Enrolled in ${module.title}",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        // Set Explore as selected
        bottomNav.selectedItemId = R.id.navigation_explore
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    finish() // Return to Compose Home
                    true
                }
                R.id.navigation_explore -> {
                    // Already here
                    true
                }
                R.id.navigation_assessment -> {
                    finish()
                    true
                }
                R.id.navigation_marketplace -> {
                    finish()
                    true
                }
                R.id.navigation_profile -> {
                    finish()
                    true
                }
                else -> false
            }
        }
    }
} 