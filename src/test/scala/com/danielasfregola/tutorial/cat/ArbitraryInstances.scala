package com.danielasfregola.tutorial.cat

import org.scalacheck.{Arbitrary, Gen}

object ArbitraryIntInstances extends ArbitraryInstances[Int]

trait ArbitraryInstances[T] {

  implicit def arbitraryMaybe(implicit arbitrary: Arbitrary[T]): Arbitrary[Maybe[T]] = Arbitrary {
    Gen.option(arbitrary.arbitrary).map {
      case Some(t) => Just(t)
      case None => Empty
    }
  }

  implicit def arbitraryZeroOrMore(implicit arbitrary: Arbitrary[T]): Arbitrary[ZeroOrMore[T]] =
    Arbitrary {
      def toZeroOrMore(l: List[T]): ZeroOrMore[T] = l match {
        case head :: tail => OneOrMore(head, toZeroOrMore(tail))
        case Nil => Zero
      }

      Gen.listOf(arbitrary.arbitrary).map(toZeroOrMore)
    }

}
