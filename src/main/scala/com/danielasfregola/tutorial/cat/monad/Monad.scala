package com.danielasfregola.tutorial.cat.monad

import com.danielasfregola.tutorial.cat.applicative.Applicative

// See solution at https://gist.github.com/DanielaSfregola/ddf48f6c5638f6284b563798c55d5ebd

trait Monad[Box[_]] extends Applicative[Box] {

  def flatMap[A, B](boxA: Box[A])(f: A => Box[B]): Box[B]

  // TODO - implement using flatMap
  def flatten[A](boxBoxA: Box[Box[A]]): Box[A] = ???

  // TODO - implement using flatMap and map
  override def ap[A, B](boxF: Box[A => B])(boxA: Box[A]): Box[B] = ???

  // TODO - implement using flatMap and pure
  override def map[A, B](boxA: Box[A])(f: A => B): Box[B] = ???

}
