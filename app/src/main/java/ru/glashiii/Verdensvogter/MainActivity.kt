package ru.glashiii.Verdensvogter

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.glashiii.Verdensvogter.databinding.ActivityMarkdownBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMarkdownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val selectMapImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                binding.mapImage.setImageURI(uri)
                if (binding.mapImage.drawable != null) {
                    binding.loadMapButton.visibility = View.GONE
                }
            }
        }
        binding.loadMapButton.setOnClickListener {
            selectMapImage.launch("image/*")
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.mainContent.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        binding.modeToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                if (checkedId == binding.previewButton.id){
                    binding.textNotEditable.text = binding.editText1.text.toString()
                    binding.textNotEditable.visibility = View.VISIBLE
                    binding.editText1.visibility = View.GONE
                }
                else {
                    binding.textNotEditable.visibility = View.GONE
                    binding.editText1.visibility = View.VISIBLE
                }
            }
        }

        binding.listButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_map -> {
                    binding.knowledgeContent.visibility = View.GONE
                    binding.mapContent.visibility = View.VISIBLE
                }
                R.id.nav_knowledge -> {
                    binding.mapContent.visibility = View.GONE
                    binding.knowledgeContent.visibility = View.VISIBLE
                }
                else -> return@setNavigationItemSelectedListener false
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }


}
