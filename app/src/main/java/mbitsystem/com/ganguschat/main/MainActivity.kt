package mbitsystem.com.ganguschat.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import mbitsystem.com.ganguschat.R

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.people -> {
                message.setText(getString(R.string.people))
                return@OnNavigationItemSelectedListener true
            }
            R.id.account -> {
                message.setText(getString(R.string.account))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initUi() {
        val adapter = MainPagerAdapter(supportFragmentManager)
        adapter.setPages(listOf(AllJokesFragment(), FavoriteJokesFragment(), ProfileFragment()))

        mainPager.adapter = adapter
        mainPager.offscreenPageLimit = 3
        bottomNavigation.setOnNavigationItemSelectedListener {
            switchNavigationTab(it.order)
            true
        }

        mainPager.onPageChange { position ->
            val item = bottomNavigation.menu.getItem(position)

            bottomNavigation.selectedItemId = item.itemId
        }

        addJoke.onClick { startActivity(Intent(this, AddJokeActivity::class.java)) }
    }
}
