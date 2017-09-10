package com.danielasfregola.tutorial.cat.functor

import com.danielasfregola.tutorial.cat._

object FunctorInstances {

  implicit def maybeFunctor: Functor[Maybe] = ???

  implicit def zeroOrMoreFunctor: Functor[ZeroOrMore] = ???

}
