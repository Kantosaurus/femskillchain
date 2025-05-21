package com.example.femskillchain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.example.femskillchain.R
import com.example.femskillchain.model.Module

class ModuleAdapter(
    private var modules: List<Module>,
    private val onEnrollClick: (Module) -> Unit
) : RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder>() {

    class ModuleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val moduleTitle: TextView = view.findViewById(R.id.moduleTitle)
        val moduleDescription: TextView = view.findViewById(R.id.moduleDescription)
        val moduleCategory: Chip = view.findViewById(R.id.moduleCategory)
        val enrollButton: MaterialButton = view.findViewById(R.id.enrollButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_module, parent, false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = modules[position]
        
        holder.moduleTitle.text = module.title
        holder.moduleDescription.text = module.description
        holder.moduleCategory.text = module.category.name.replace("_", " ")
        
        // Update enroll button state
        holder.enrollButton.text = if (module.isEnrolled) "Enrolled" else "Enroll"
        holder.enrollButton.isEnabled = !module.isEnrolled

        holder.enrollButton.setOnClickListener {
            onEnrollClick(module)
        }
    }

    override fun getItemCount() = modules.size

    fun updateModules(newModules: List<Module>) {
        modules = newModules
        notifyDataSetChanged()
    }
} 