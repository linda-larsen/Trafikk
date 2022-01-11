package com.example.trafikkskilt.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

/**
 * The app introduction
 * Currently not in use
 */
class Intro : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSlide(AppIntroFragment.newInstance(
            title = "Welcome!",
            description = "Hoe"
        ))
        addSlide(AppIntroFragment.newInstance(
            title = "first",
            description = "you gotta leeern bish"
        ))


    }
     override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
         finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }
}
