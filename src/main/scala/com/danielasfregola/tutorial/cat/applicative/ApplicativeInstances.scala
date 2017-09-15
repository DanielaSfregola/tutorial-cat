package com.danielasfregola.tutorial.cat.applicative

import com.danielasfregola.tutorial.cat._

object ApplicativeInstances {

  implicit val maybeApplicative: Applicative[Maybe] = ???

  implicit val zeroOrMoreApplicative: Applicative[ZeroOrMore] = ???

}
