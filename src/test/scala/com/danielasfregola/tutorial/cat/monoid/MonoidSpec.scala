package com.danielasfregola.tutorial.cat.monoid

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Properties}

import scala.reflect._

class IntMonoidSpec extends MonoidSpec[Int](MonoidInstances.intMonoid)
class StringMonoidSpec extends MonoidSpec[String](MonoidInstances.stringMonoid)

abstract class MonoidSpec[A: ClassTag](monoid: Monoid[A])(implicit arbitrary: Arbitrary[A]) extends Properties(s"Monoid for ${classTag[A]}") {

  val id = monoid.identity

  // n o id == id o n == n
  property("identity") = forAll { n: A =>
    monoid.compose(n, id) == n &&
    monoid.compose(id, n) == n
  }

  // forall x, y => x o y
  property("composition") = forAll { (x: A, y: A) =>
    monoid.compose(x, y).isInstanceOf[A]
  }

  // x o (y o z) == (x o y) o z
  property("associativity") = forAll { (x: A, y: A, z: A) =>
    val xY = monoid.compose(x,y)
    val yZ = monoid.compose(y,z)
    monoid.compose(xY, z) == monoid.compose(x, yZ)
  }
}
