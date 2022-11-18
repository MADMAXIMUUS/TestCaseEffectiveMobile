package ru.madmax.testcaseeffectivemobile

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.madmax.testcaseeffectivemobile.databinding.ActivityMainBinding
import ru.madmax.testcaseeffectivemobile.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}