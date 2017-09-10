package com.danielasfregola.tutorial.cat

sealed abstract class ZeroOrMore[+A]

final case class OneOrMore[A](head: A, tail: ZeroOrMore[A]) extends ZeroOrMore[A]
case object Zero extends ZeroOrMore[Nothing]
