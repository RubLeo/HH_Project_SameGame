**Please read carefully all the sections on this page before you start
working on your project!**

As part of the requirements to pass the course you have to do a
programming project. Your project work consists of three parts, all of
which you have to complete to a sufficient degree:

1.  Application/framework code (properly **documented**!),

2.  Report describing your work, due at the end of the 1st exam week
    after the course, that is **May 24th**.

3.  Final presentation with slides on **May 20th/21st**. 

The details of the project description / requirements are given in the
next document in the same folder named \"The Project Specification\".
You have to work in the same groups that you formed for the assignments.
During the last three weeks of the course the teaching hours are devoted
to project supervision. You have **roughly five weeks** to work on the
project. The requirements for the different grades are given in the
project description, but note that all of your code, report, and
presentation contribute to your grade.

As for the agenda for the presentation days, details will follow in due
time, including a spreadsheet to book your presentation slot.

**Project Supervision**

The project supervision will start after the lecturing weeks, so in
weeks 6, 7, and 8 of the course, and it will replace the lectures and
lab supervision. It is much better to prepare the questions you want to
ask ahead of time (come prepared), supervision is based on your
questions, the sessions are not meant to guide you through your project
work, but to support it.

**Tile / grid game SameGame**

Your task is is to design and develop in Java a tile based puzzle
(non-action) game SameGame, see
[[https://en.wikipedia.org/wiki/SameGame]{.underline}](https://en.wikipedia.org/wiki/SameGame) for
detailed description of the game. Your design should follow the
principles taught in the course and utilize the design patterns you have
learned about. In particular, you should:

-   Consider the main design around the Model-View-Controller or
    Subject-Observer patterns, or a mixture of both. To demonstrate
    this, your game should provide two distinct \"views\" of the game
    state. One obvious view is the graphical output (you can use a Swing
    frame), the other one could be a console based text view of the game
    (e.g. for debugging of the game engine, logging, etc.), or a game
    \"capture view\" (this one does not necessarily show anything, but
    remembers all subsequent game states / views and records them in a
    list to be later, e.g., replayed or analysed). You should provide at
    least two different views of the game and show that you can easily
    register / de-register them in your application.

-   Consider a game model that keeps track of the set difficulty level
    (number of colors in use), the current state of the game, accepts
    controlling events to update the state, checks for end-of-game /
    winning / loosing conditions, and notifies observers whenever
    necessary,

-   Consider re-pluggable methods for providing different ways of
    controlling the game. One could be the keyboard, one could be the
    mouse, yet another could be your mobile phone (with an app that
    you *do not have* to write) over a network connection. These methods
    should be repluggable in your design, you could use e.g. the
    Strategy pattern for this, or any other suitable pattern. At least
    one default method for providing input should be implemented. Also,
    the different input methods do not have to be able to work together
    with each other (but they can).

By no means you are limited to the above requirements, you can extend
the idea any way you see fit and apply other patterns. To visualize the
game, it is sufficient to use uniformly colored shapes (refer to the
circle icon task in the first set of exercises), but you can also use
suitable bitmap graphics that you may find on the Internet.

Fulfilling these basic requirements should get you a **grade of 3**,
provided of course the principles are applied in the correct way and you
end up with a functioning program. Additionally the presentation and
report is required to get a passing grade (see sections below).

**Note:** the purpose of the project **is not** fancy graphics or
animations (the existing SameGame implementations go quite far with
things like this), in particular (if you are familiar with it)
you **should not** use the game loop design pattern and do detailed
frame by frame renderings of the screen (that is, it is perfectly fine
and enough to update the game view only once per game state change, and
this is usually triggered by user input). What is being judged is the
design, use of Java, and familiarity with OOP principles from the
course, not the ability of programming graphics.

**Extensions**

On top of the basic requirements you can develop the following
extensions to shoot for **grade 4**:

-   A system for sound notifications using the observer pattern. This
    should be separate from the graphical / visual game view. The game
    model under predefined (by you) conditions produces events, for
    which a dedicated event observer should generate sounds. 

-   A facility for the game to provide the next move suggestion (game
    helper / cheater) to point the player to the most scoring move to
    perform next.

-   A facility to save and load the list of high scores achieved so far,
    preferably by using Java serialization (we do not cover this in
    depth in the course, so it requires a bit of self study (Section 7.5
    of the book covers it), but the concept is not too difficult).

-   We also expect the code to be properly documented with JavaDoc and
    see attempts of testing your code using **JUnit**.

**Framework**

To attempt **grade 5** for the project you should design your code
around the principle of a framework, so that you can easily implement
other (but similar) games to SameGame with minimal effort. It is up to
you how to design the framework for most reusability, you should,
however, document the design of your framework sufficiently in the
report and demonstrate its usability by implementing another game with
your framework, namely the *2048 game*, see
here: [[https://2048game.com]{.underline}](https://2048game.com/).
Alternatively, you can show case your framework by implementing the game
Sokoban, see
[[https://en.wikipedia.org/wiki/Sokoban]{.underline}](https://en.wikipedia.org/wiki/Sokoban).
For this one you may want to use the attached game tile graphics that we
extracted from the quoted Wikipedia page:

**sokoban_icons.zip**

**Project code maintenance - version control**

As an exercise in code management in real software development and also
to facilitate the division of work within your project groups, we ask
you to maintain your code through one of the publicly available version
control systems, for
example [GITHub]{.underline} or [[Bitbucket]{.underline}](https://bitbucket.org).
To do this, set up an account for each project member (unless you have
one already and want to use it), create a **private** (this is
important, we do not want the other groups to be peeking into your
developments) repository for your project. You should also shortly
describe your experiences with version control in the project report.

**Project code and report submission**

The final project code and report are to be submitted through the
Blackboard submission system, a suitable entry will be created in due
time and available in the *Project Assignment* section. When submitting,
consider the following:

1.  Code should be properly indented and formatted.

2.  Code should be documented, explain the role of the parameters to
    methods and constructors, as well as the general code architecture.

3.  Code should not be riddled with debugging statements.

To submit your project prepare a **single ZIP file** with all the
(source) files that make up your project, including the **PDF with the
report**. We know that having the code on GITHub or elsewhere should be
enough, but this also allows us to see that you are done with it (and
also your repository should be *private, *see above).

**The project material submission is due on May 24th (end of the week of
the presentations)!**

You will have to write a report as part of your project work. The report
is very important and is part of what gets evaluated for your grade.
Take into account that there are several aspects that make a good report
and that it is very difficult to write (for most of us at least!). These
aspects include:

-   your use of language: clarity, succinctness, accuracy, also spelling
    and grammar, but these last two are not graded.

-   your use of structure: sentences, paragraphs, chapters, figures,
    code listings, diagrams.

-   your use of other's work: citations and references.

-   your use of the concepts you have learnt in the course: patterns,
    interfaces, other technical terms.

-   your use of typography: titles, code listings, formulas, text.

We do not expect a very long report, but it should be long enough to
include:

1.  A good and informative **title**.

2.  An **introduction** that explains the problem that you solved. Even
    if you were given a project specification it is important that you
    can write down what kind of software you were expected to produce
    and what particular decisions you made. You should be able to
    discuss what requirements of functionality, design, implementation
    and testing were imposed. It can be interesting to know whether you
    have seen similar programs and in what way your program has more or
    less functionality.

3.  A **design part** where you explain the organisation of your program
    and what goals you achieve by organizing it the way you do. Is it to
    allow for extensions? Is it to facilitate modifications? Is it to
    make some parts very efficient? Is it because you can re-use what
    others have done? Is it because it will be easier to test and make
    sure it is of good quality? In this section we expect to see some
    diagrams and maybe some code.

4.  A **testing section** where you explain how you proceeded to test
    your program. What parts were tested? How did you organize your
    tests? What (if) parts were tested using automatic testing
    techniques? In this section we expect to see short code listings
    related to testing.

5.  A section where you discuss some **interesting parts** of your
    program and you maybe show some code. There has to be some
    interesting problem that is related to coding and that you solved in
    an interesting and professional way. Maybe you started trying to
    apply a design pattern but you needed to tweak it in a special way?
    In this section we expect also to see code listings, but brief.

6.  A section where you discuss **results**. Did you manage to implement
    all functionality? Does it work as intended? Were you able to follow
    the requirements word-by-word? Can you explain how good your program
    is in case you want to extend with new functionality? Can you
    explain why you program is so easy to modify in case a better
    implementation of a part is possible?

7.  A short section on your experiences with **version control / GIT**.

8.  A section where you list the sources of information or code that you
    refer to in your report, that is **Bibliography / References**.

The report should be submitted along with the project code and has **the
same deadline as above, May 24th**.

You will have to make a presentation as part of your project work. The
presentation is very important and is part of what gets evaluated for
your grade.

Each group will get **15 minutes** for the presentation. The time should
be used in the following way:

1.  At most **10 minutes** for a slide presentation of a description of
    your project, including how you interpreted the project and choices
    you made, how you organized the program, what design patterns you
    used, and how classes are related.

2.  At most **3 minutes** to demonstrate your program(s).

3.  At most **2 minutes** to answer questions.

For the slide presentation think of using images and figures, not too
much text: you can talk to the figures instead. It is difficult to use
less than **1.5 minutes** to talk to one slide, so you should have at
most **7 slides** in your presentation. Do take into account that all of
us in the audience are familiar with programming in Java but we do not
know what classes you used and how you put them together. Also, take
into account that we will be examining you: we want to see that you
achieved the learning outcomes of the course.

The final project presentations will take place on **Monday and Tuesday,
May 20th/21st in R1205**. We will make **45 min. slots during the two
days, each slot for 3 groups**, during the two days, a link to sign up
for the slots will be send in due time. **Your group is required to be
present through the complete 45 minute slot to give your own
presentation and listen to other two groups and possibly ask them
questions.** Also, because there will surely be small slip-ups, plan
your attendance with time flexibility (meaning that the slots will not
necessarily start exactly on the initially scheduled time, nor they will
end on one).
