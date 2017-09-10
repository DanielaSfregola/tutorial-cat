package com.danielasfregola.tutorial.cat.monoid

object MonoidInstances {

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {

    override def compose(x: Int, y: Int): Int = x + y

    override def identity: Int = 0
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {

    override def compose(x: String, y: String): String = x + y

    override def identity: String = ""
  }

}
