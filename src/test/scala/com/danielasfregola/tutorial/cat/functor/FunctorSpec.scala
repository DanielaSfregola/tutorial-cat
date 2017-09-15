package com.danielasfregola.tutorial.cat.functor

import com.danielasfregola.tutorial.cat._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Properties}
import FunctorInstances._
import ArbitraryIntInstances._

import scala.reflect._

class MaybeFunctorSpec extends FunctorSpec(maybeFunctor)
class ZeroOrMoreFunctorSpec extends FunctorSpec(zeroOrMoreFunctor)

abstract class FunctorSpec[Box[_]](val functor: Functor[Box])(implicit val arbitrary: Arbitrary[Box[Int]],
                                                              tag: ClassTag[Box[_]])
    extends Properties(s"Functor for $tag")
    with FunctorProperties[Box]

trait FunctorProperties[Box[_]] extends SimpleCategoryUtils { self: Properties =>

  val functor: Functor[Box]
  import functor._

  implicit def arbitrary: Arbitrary[Box[A]]

  lazy val mapF: Box[A] => Box[B] = map(_)(f)
  lazy val mapG: Box[B] => Box[C] = map(_)(g)
  lazy val mapH: Box[C] => Box[D] = map(_)(h)

  // map_id == id
  property("identity") = forAll { box: Box[A] =>
    map(box)(identity) == box
  }

  // map_(g o f) == (map_g) o (map_f)
  property("composition") = forAll { boxA: Box[A] =>
    val fG = f andThen g
    val mapFG: Box[A] => Box[C] = map(_)(fG)
    mapFG(boxA) == (mapF andThen mapG)(boxA)
  }

  // map_(h o g) o map_f == map_h o map_(g o f)
  property("associativity") = forAll { boxA: Box[A] =>
    val fG = f andThen g
    val mapFG: Box[A] => Box[C] = map(_)(fG)
    val gH = g andThen h
    val mapGH: Box[B] => Box[D] = map(_)(gH)

    (mapF andThen mapGH)(boxA) == (mapFG andThen mapH)(boxA)
  }
}
