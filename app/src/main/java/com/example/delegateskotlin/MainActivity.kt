package com.example.delegateskotlin

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.appbar.MaterialToolbar

/*
One of the things we used to do when starting new Android projects is set its Base classes, like
BaseActivity, BaseFragment, BaseViewModel, BaseRecyclerAdapter…. and so on. So we have a structure
to follow and not repeat our code over and over again.

But wait a minute, is that following the SOLID principles? What about the Single Responsibility principle?
What about big God basses?
What about all child classes has a functionality that they may not use at all? What about making a
change in that Base class? Is that
going to break all classes that inherit from it? What if we need different variants of that Base class?

We can see that the benefit of bases comes with many problems and high risks. Any change of that
base requires changing a lot of classes, a lot of time, a lot of effort, and a lot of testing….
because things are tightly coupled.

In general, to reuse our code, we have multiple relationship options :
1- Inheritance
 It derives one class from another, so the relationship is: “is-a”, Like Cat is an Animal.
 It should be avoided in general in favor of association if we can. Also, in OOP,
 we can’t inherit from multiple classes.
For more info on this, see the Diamond Problem.

2- Association
 It defines a class as the sum of its parts (other classes). So whenever we need
 some functionality, we can call other objects and use their functionality
 in our class. Here the relationship is: “has-a”, Like Car has-an Engine.
 Association can be achieved by :
    1- Aggregation
        It is a weak association like every employee has an access card.
        Employees can exist on their own, and so the access card is.
    2- Composition
        It is a strong association, like a house has-a room, but a room can’t
        exist without a house. So the lifecycle of a room is controlled by the house.
        After all this, can we use association just like inheritance? Yes, Here is where Delegates shine!
        Let’s start with the fun part. I will demonstrate how delegates work with adding
        the functionality of a ToolBar to an Android Activity.

    To apply the delegate's concept, we have to follow 4 steps:
 */

// step 1: Define the functionality that this delegate is responsible for.
interface ToolbarDelegate {

    fun addToolbar(toolbar: MaterialToolbar, title: String? = null,
                   enableHomeAsUp: Boolean = false, homeAsUpDrawable: Drawable? = null)

    fun setToolbarTitle(newTitle: String?)

    fun setActivityForToolbarDelegate(activity: AppCompatActivity?)
}

// step 2: Implement this functionality.
class ToolbarDelegateImpl : ToolbarDelegate {

    private var toolbar: MaterialToolbar? = null
    private var activity: AppCompatActivity? = null

    override fun addToolbar(
        toolbar: MaterialToolbar,
        title: String?,
        enableHomeAsUp: Boolean,
        homeAsUpDrawable: Drawable?
    ) {
        this.toolbar = toolbar

        activity?.setSupportActionBar(toolbar)

        activity?.supportActionBar?.let { supportActionBar ->
            supportActionBar.setDisplayHomeAsUpEnabled(enableHomeAsUp)
            homeAsUpDrawable?.let {
                supportActionBar.setHomeAsUpIndicator(it)
            }
        }

        setToolbarTitle(title)
    }

    override fun setToolbarTitle(newTitle: String?) {
        toolbar?.title = newTitle
    }

    override fun setActivityForToolbarDelegate(activity: AppCompatActivity?) {
        this.activity = activity
    }

}

// Step: 3
// Instantiate any of these delegate implementations.

// Step: 4
// Make your class implement this delegate interface, then delegate its functionality.


class MainActivity : AppCompatActivity(), ToolbarDelegate {

    private val toolbarDelegate : ToolbarDelegate = ToolbarDelegateImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarDelegate.addToolbar(
            toolbar = findViewById(R.id.toolbar),
            title = getString(R.string.app_name),
            enableHomeAsUp = true
        )

        var button: Button = findViewById(R.id.button)
        button.setOnClickListener { startActivity(Intent(this, Activity2::class.java)) }

        Log.d("checkLC", "onCreate Main")
    }

    override fun addToolbar(
        toolbar: MaterialToolbar,
        title: String?,
        enableHomeAsUp: Boolean,
        homeAsUpDrawable: Drawable?
    ) {
        toolbarDelegate.addToolbar(toolbar, title, enableHomeAsUp)
    }

    override fun setToolbarTitle(newTitle: String?) {
        toolbarDelegate.setToolbarTitle(newTitle)
    }

    override fun setActivityForToolbarDelegate(activity: AppCompatActivity?) {
        toolbarDelegate.setActivityForToolbarDelegate(activity)
    }

    override fun onStart() {
        super.onStart()
        Log.d("checkLs", "onStart Main")
    }

    override fun onResume() {
        Log.d("checkLc", "onResume Main")
        super.onResume()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("checkLc", "onRestart Main")
    }

    override fun onPause() {
        super.onPause()
        Log.d("checkLc", "onPause Main")
    }

    override fun onStop() {
        super.onStop()
        Log.d("checkLc", "onStop Main")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("checkLc","onDestroy Main")
    }


}