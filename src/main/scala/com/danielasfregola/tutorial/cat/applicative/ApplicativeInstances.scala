package com.danielasfregola.tutorial.cat.applicative

import com.danielasfregola.tutorial.cat._

object ApplicativeInstances {

  implicit def maybeApplicative: Applicative[Maybe] = ???

  // BONUS: can you write an applicative for zero or more?
  // Do not forget to test it!
  implicit def zeroOrMoreApplicative: Applicative[ZeroOrMore] = ???

}
