package com.example.cubeanimation

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_cube.*
import java.util.*
import android.graphics.Color
import android.view.animation.AnimationUtils


open class MainActivity : AppCompatActivity() {
    lateinit var myViewPagerAdapter: MyViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_english.setOnClickListener {
            val animFlip = AnimationUtils.loadAnimation(applicationContext,R.anim.flip)
            setLanguage("en", this)
            viewPager.startAnimation(animFlip)
        }
        btn_vn.setOnClickListener {
            setLanguage("vi", this)
        }
        btn_start_activity.setOnClickListener {
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        myViewPagerAdapter = MyViewPagerAdapter()
        viewPager.setPageTransformer(true, CubeTransformer())
        viewPager.adapter = myViewPagerAdapter
    }

    fun setLanguage(language: String, context: Context) {
        val config = resources.configuration
        val locale = Locale(language)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            config.setLocale(locale)
        else
            config.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
        viewPager.setPageTransformer(true, CubeTransformer())
        viewPager.adapter = myViewPagerAdapter

    }

    class MyViewPagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return 10
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var layoutInflater = LayoutInflater.from(container.context)
            var constraintLayout = layoutInflater.inflate(R.layout.custom_cube, container, false)
            container.addView(constraintLayout)
            return constraintLayout
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
            notifyDataSetChanged()
        }

    }
}