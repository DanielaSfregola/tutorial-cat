package com.danielasfregola.tutorial.cat

trait Category {

  type A
  type B
  type C
  type D

  def f(a: A): B
  def g(b: B): C
  def h(c: C): D

}

object SimpleCategory extends Category {

  type A = Int
  type B = Double
  type C = String
  type D = Int

  def f(a: A): B = a + 2.0
  def g(b: B): C = b + "hello"
  def h(c: C) = c.length
}
