package monoid

trait Monoid[A] {

  def identity: A

  def compose(x: A, y: A): A
}
