package ru.glashiii.Verdensvogter

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.glashiii.Verdensvogter.databinding.ActivityMarkdownBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMarkdownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
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
    }


}