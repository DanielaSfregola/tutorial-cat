# tutorial-cat

Code for the `Pragmatic Introduction to Category Theory` workshop at [ScalaWorld 2017](https://scala.world)

## Installation
- clone the repo
- `sbt test:compile`
- `sbt run`
- if you see the secret message you are good to go!

## Agenda

### Intro

### Monoid
- Define a monoid for `Int`
- Define a monoid for `String`

`sbt 'testOnly *Monoid*'`


### Functor
- Define a functor for `Maybe`
- Define a functor for `ZeroOrMore`

`sbt 'testOnly *Functor*'`


### Applicative
- Define `map` in terms of `ap` and `pure`
- Define an applicative for `Maybe`
- Define an applicative for `ZeroOrMore`

`sbt 'testOnly *Applicative*'`

### Monad (1)
- Define `flatten` using `flatMap`
- Define `map` using `flatMap` and `pure`
- Define `ap` using `flatMap` and `map`

### Monad (2)
- Define a monad for `Maybe`
- Define a monad for `ZeroOrMore`

`sbt 'testOnly *Monad*'`
