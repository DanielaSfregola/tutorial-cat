package com.danielasfregola.tutorial.cat.applicative

import com.danielasfregola.tutorial.cat.functor.Functor

// See solution at https://gist.github.com/DanielaSfregola/ddf48f6c5638f6284b563798c55d5ebd

trait Applicative[Box[_]] extends Functor[Box] {

  def pure[A](a: A): Box[A]

  def ap[A, B](boxF: Box[A => B])(boxA: Box[A]): Box[B]

  // TODO - implement using pure and app
  override def map[A, B](boxA: Box[A])(f: A => B): Box[B] = ???

}
