package com.danielasfregola.tutorial.cat.applicative

import com.danielasfregola.tutorial.cat.ArbitraryIntInstances._
import ApplicativeInstances._
import com.danielasfregola.tutorial.cat._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Properties}

import scala.reflect._

class MaybeApplicativeSpec extends ApplicativeSpec[Maybe](maybeApplicative)

// BONUS: can you write an applicative for zero or more? Uncomment to test it:
//class ZeroOrMoreApplicativeSpec extends ApplicativeSpec[ZeroOrMore](zeroOrMoreApplicative)

abstract class ApplicativeSpec[Box[_]](applicative: Applicative[Box])(implicit arbitrary: Arbitrary[Box[Int]],
                                                          tag: ClassTag[Box[_]])
    extends Properties(s"Applicative for $tag") {

  val cat = SimpleCategory

  type A = cat.A
  type B = cat.B
  type C = cat.C
  type D = cat.D

  val f: A => B = cat.f
  val g: B => C = cat.g
  val h: C => D = cat.h

  // ap(id)(a) == a
  property("identity") = forAll { box: Box[A] =>
    val identityF: Box[A => A] = applicative.pure(identity)
    applicative.ap(identityF)(box) == box
  }

  // ap(pure(f))(pure(a)) == pure(f(a))
  property("homorphism") = forAll { a: A =>
    val pureF = applicative.pure(f)
    val pureA = applicative.pure(a)
    applicative.ap(pureF)(pureA) == applicative.pure(f(a))
  }

  // {x => pure(x)}(a) == pure(a)
  property("interchange") = forAll { a: A =>
    val toPureF = { x: A => applicative.pure(x)}
    toPureF(a) == applicative.pure(a)
  }

  // pure(h o g o f) == ap(pure(h o g))(pure(f(a)))
  property("composition") = forAll { a: A =>
    val gH = g andThen h
    val fGH = f andThen gH
    val pureGH = applicative.pure(gH)
    val pureFA = applicative.pure(f(a))
    applicative.pure(fGH(a)) == applicative.ap(pureGH)(pureFA)
  }
}
