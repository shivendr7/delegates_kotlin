## Hello Delegates, Goodbye Base classes

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

## 1- Inheritance
 It derives one class from another, so the relationship is: “is-a”, Like Cat is an Animal.
 It should be avoided in general in favor of association if we can. Also, in OOP,
 we can’t inherit from multiple classes.
For more info on this, see the Diamond Problem.

## 2- Association
 It defines a class as the sum of its parts (other classes). So whenever we need
 some functionality, we can call other objects and use their functionality
 in our class. Here the relationship is: “has-a”, Like Car has-an Engine.
 Association can be achieved by :
    ### 1- Aggregation
        It is a weak association like every employee has an access card.
        Employees can exist on their own, and so the access card is.
    ### 2- Composition
        It is a strong association, like a house has-a room, but a room can’t
        exist without a house. So the lifecycle of a room is controlled by the house.
        After all this, can we use association just like inheritance? Yes, Here is where Delegates shine!
        Let’s start with the fun part. I will demonstrate how delegates work with adding
        the functionality of a ToolBar to an Android Activity.
