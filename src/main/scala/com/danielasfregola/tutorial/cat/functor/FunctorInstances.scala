package com.danielasfregola.tutorial.cat.functor

import com.danielasfregola.tutorial.cat._

object FunctorInstances {

  implicit val maybeFunctor: Functor[Maybe] = ???

  implicit val zeroOrMoreFunctor: Functor[ZeroOrMore] = ???

}
