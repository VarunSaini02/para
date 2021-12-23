package com.ait.para

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val ivSplash = findViewById<ImageView>(R.id.ivSplash)
        val anim = AnimationUtils.loadAnimation(
            this,
            R.anim.logo_animation
        )
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(Intent(this@SplashActivity, HomeScreenActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })

        ivSplash.startAnimation(anim)
    }
}