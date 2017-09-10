package com.danielasfregola.tutorial.cat.functor

import com.danielasfregola.tutorial.cat._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Properties}
import FunctorInstances._
import ArbitraryIntInstances._

import scala.reflect._

class MaybeFunctorSpec extends FunctorSpec[Maybe](maybeFunctor)

class ZeroOrMoreFunctorSpec extends FunctorSpec[ZeroOrMore](zeroOrMoreFunctor)

abstract class FunctorSpec[Box[_]](functor: Functor[Box])(implicit arbitrary: Arbitrary[Box[Int]],
                                                          tag: ClassTag[Box[_]])
    extends Properties(s"Functor for $tag") {

  val cat = SimpleCategory

  type A = cat.A
  type B = cat.B
  type C = cat.C
  type D = cat.D

  val f: A => B = cat.f
  val g: B => C = cat.g
  val h: C => D = cat.h

  val mapF: Box[A] => Box[B] = functor.map(_)(f)
  val mapG: Box[B] => Box[C] = functor.map(_)(g)
  val mapH: Box[C] => Box[D] = functor.map(_)(h)

  // map_id == id
  property("identity") = forAll { box: Box[A] =>
    functor.map(box)(identity) == box
  }

  // map_(g o f) == (map_g) o (map_f)
  property("composition") = forAll { boxA: Box[A] =>
    val fG = f andThen g
    val mapFG: Box[A] => Box[C] = functor.map(_)(fG)
    mapFG(boxA) == (mapF andThen mapG)(boxA)
  }

  // map_(h o g) o map_f == map_h o map_(g o f)
  property("associativity") = forAll { boxA: Box[A] =>
    val fG = f andThen g
    val mapFG: Box[A] => Box[C] = functor.map(_)(fG)
    val gH = g andThen h
    val mapGH: Box[B] => Box[D] = functor.map(_)(gH)

    (mapF andThen mapGH)(boxA) == (mapFG andThen mapH)(boxA)
  }
}
