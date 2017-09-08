package monoid

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Properties}

class IntMonoidSpec extends MonoidSpec[Int](MonoidInstances.intMonoid)
class StringMonoidSpec extends MonoidSpec[String](MonoidInstances.stringMonoid)

abstract class MonoidSpec[A: Manifest](monoid: Monoid[A])(implicit arbitrary: Arbitrary[A]) extends Properties(s"Monoid for ${manifest[A]}") {

  val id = monoid.identity

  property("identity is respected") = forAll { n: A =>
    monoid.compose(n, id) == n &&
    monoid.compose(id, n) == n
  }

  property("composition is associative") = forAll { (x: A, y: A, z: A) =>
    val xY = monoid.compose(x,y)
    val yZ = monoid.compose(y,z)
    monoid.compose(xY, z) == monoid.compose(x, yZ)
  }
}
