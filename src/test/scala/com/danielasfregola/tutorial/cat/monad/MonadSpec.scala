package com.danielasfregola.tutorial.cat.monad

import com.danielasfregola.tutorial.cat.ArbitraryIntInstances._
import MonadInstances._
import com.danielasfregola.tutorial.cat.applicative.ApplicativeProperties
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Properties}

import scala.reflect._

class MaybeMonadSpec extends MonadSpec(maybeMonad)
class ZeroOrMoreMonadSpec extends MonadSpec(zeroOrMoreMonad)

abstract class MonadSpec[Box[_]](val monad: Monad[Box])(implicit val arbitrary: Arbitrary[Box[Int]],
                                                        tag: ClassTag[Box[_]])
    extends Properties(s"Monad for $tag")
    with MonadProperties[Box]

trait MonadProperties[Box[_]] extends ApplicativeProperties[Box] { self: Properties =>

  val monad: Monad[Box]
  import monad._

  lazy val applicative = monad

  lazy val toPureF = { a: A => pure(f(a)) }
  lazy val toPureG = { b: B => pure(g(b)) }

  // flatMap(pure(a))(f(a)) == f(a)
  property("left identity") = forAll { a: A =>
    flatMap(pure(a))(toPureF) == toPureF(a)
  }

  // flatMap(pure(f(a)))(pure) == pure(f(a))
  property("right identity") = forAll { a: A =>
    flatMap(toPureF(a))(pure) == toPureF(a)
  }

  // flatMap(flatMap(boxA)(boxF))(boxG) == flatMap(boxA)(boxF(_))(boxG)
  property("associativity") = forAll { boxA: Box[A] =>
    val left: Box[C] = flatMap(flatMap(boxA)(toPureF))(toPureG)
    val right: Box[C] = flatMap(boxA)(a => flatMap(toPureF(a))(toPureG))
    left == right
  }
}
