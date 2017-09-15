package com.danielasfregola.tutorial.cat.applicative

import com.danielasfregola.tutorial.cat.functor.Functor

trait Applicative[Box[_]] extends Functor[Box] {

  def pure[A](a: A): Box[A]

  def ap[A, B](boxF: Box[A => B])(boxA: Box[A]): Box[B]

  // TODO - implement using pure and app
  override def map[A, B](boxA: Box[A])(f: A => B): Box[B] = ???

}
