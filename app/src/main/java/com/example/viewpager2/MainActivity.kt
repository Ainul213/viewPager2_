package com.example.viewpager2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Setup WindowInsets untuk padding
        setupWindowInsets()

        // Setup ViewPager dan TabLayout
        setupViewPagerWithTabs()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupViewPagerWithTabs() {
        // Inisialisasi ViewPager2
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter(fragmentActivity = this)

        // Inisialisasi TabLayout dan hubungkan dengan ViewPager2
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> configureTab(tab, "Kawan", R.drawable.friends)
                1 -> configureTab(tab, "Github", R.drawable.github)
                2 -> configureTab(tab, "Profile", R.drawable.profile)
            }
        }.attach()
    }

    private fun configureTab(tab: TabLayout.Tab, title: String, iconRes: Int) {
        tab.text = title
        tab.setIcon(iconRes)
    }
}
