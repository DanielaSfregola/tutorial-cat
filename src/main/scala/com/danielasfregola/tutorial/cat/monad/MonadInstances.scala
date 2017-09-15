package com.danielasfregola.tutorial.cat.monad

import com.danielasfregola.tutorial.cat._
import com.danielasfregola.tutorial.cat.applicative.ApplicativeInstances._

object MonadInstances {

  implicit val maybeMonad: Monad[Maybe] = ???

  implicit val zeroOrMoreMonad: Monad[ZeroOrMore] = ???

}
