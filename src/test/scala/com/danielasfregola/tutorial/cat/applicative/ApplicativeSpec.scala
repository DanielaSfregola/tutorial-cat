package com.danielasfregola.tutorial.cat.applicative

import com.danielasfregola.tutorial.cat.ArbitraryIntInstances._
import ApplicativeInstances._
import com.danielasfregola.tutorial.cat.functor.FunctorProperties
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Properties}

import scala.reflect._

class MaybeApplicativeSpec extends ApplicativeSpec(maybeApplicative)
class ZeroOrMoreApplicativeSpec extends ApplicativeSpec(zeroOrMoreApplicative)

abstract class ApplicativeSpec[Box[_]](val applicative: Applicative[Box])(implicit val arbitrary: Arbitrary[Box[Int]],
                                                                          tag: ClassTag[Box[_]])
    extends Properties(s"Applicative for $tag")
    with ApplicativeProperties[Box]

trait ApplicativeProperties[Box[_]] extends FunctorProperties[Box] { self: Properties =>

  val applicative: Applicative[Box]
  import applicative._

  val functor = applicative

  val pureIdentity: Box[A => A] = pure(identity)
  val pureF = pure(f)
  val toPureA = { a: A => pure(a)}

  // ap(id)(a) == a
  property("identity") = forAll { box: Box[A] =>
    ap(pureIdentity)(box) == box
  }

  // ap(pure(f))(pure(a)) == pure(f(a))
  property("homorphism") = forAll { a: A =>
    ap(pureF)(pure(a)) == pure(f(a))
  }

  // {x => pure(x)}(a) == pure(a)
  property("interchange") = forAll { a: A =>
    toPureA(a) == pure(a)
  }

  // pure(h o g o f) == ap(pure(h o g))(pure(f(a)))
  property("composition") = forAll { a: A =>
    val gH = g andThen h
    val fGH = f andThen gH
    val pureGH = pure(gH)
    val pureFA = pure(f(a))
    pure(fGH(a)) == ap(pureGH)(pureFA)
  }
}
