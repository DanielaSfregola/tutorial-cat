package com.danielasfregola.tutorial.cat.monad

import com.danielasfregola.tutorial.cat._
import com.danielasfregola.tutorial.cat.applicative.ApplicativeInstances._

// See solution at https://gist.github.com/DanielaSfregola/ddf48f6c5638f6284b563798c55d5ebd

object MonadInstances {

  implicit val maybeMonad: Monad[Maybe] = ???

  implicit val zeroOrMoreMonad: Monad[ZeroOrMore] = ???

}
