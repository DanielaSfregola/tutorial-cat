package com.danielasfregola.tutorial.cat

sealed abstract class ZeroOrMore[+A] {
  def append[B >: A](other: ZeroOrMore[B]): ZeroOrMore[B]
}

final case class OneOrMore[A](head: A, tail: ZeroOrMore[A]) extends ZeroOrMore[A] {

  def append[B >: A](other: ZeroOrMore[B]): ZeroOrMore[B] =
    OneOrMore(head, tail.append(other))
}

case object Zero extends ZeroOrMore[Nothing] {

  def append[B](other: ZeroOrMore[B]): ZeroOrMore[B] = other

}
