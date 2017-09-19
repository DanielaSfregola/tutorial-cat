package com.danielasfregola.tutorial.cat.functor

import com.danielasfregola.tutorial.cat._

// See solution at https://gist.github.com/DanielaSfregola/ddf48f6c5638f6284b563798c55d5ebd

object FunctorInstances {

  implicit val maybeFunctor: Functor[Maybe] = ???

  implicit val zeroOrMoreFunctor: Functor[ZeroOrMore] = ???

}
