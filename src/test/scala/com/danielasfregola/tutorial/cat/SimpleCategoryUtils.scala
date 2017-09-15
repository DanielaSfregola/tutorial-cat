package com.danielasfregola.tutorial.cat

trait SimpleCategoryUtils {

  type A = Int
  type B = Double
  type C = String
  type D = Int

  val f: A => B = _ + 2.0
  val g: B => C = _ + "hello"
  val h: C => D = _.length

}
