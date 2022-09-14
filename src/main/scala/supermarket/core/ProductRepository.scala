package supermarket.core

import cats.effect.unsafe.implicits.global
import doobie.implicits._

import supermarket.model.{Product,ProductToDBProtocol}

object ProductRepository extends ProductToDBProtocol{

  def getAllProducts():Either[Int,List[Product]] = {
    val IOqueryDB =
      sql"select * from products"
        .query[Product]
        .to[List]
        .transact(xa)

    IOqueryDB.unsafeRunSync() match {
      case x: List[Product] => Right(x)
      case _ => Left(0)
    }
  }

  def getProductByID(id :Int):Either[Int, Option[Product]] = {
    val IOqueryDB =
      sql"select * from products where id=$id"
        .query[Product]
        .option
        .transact(xa)

    IOqueryDB.unsafeRunSync() match {
      case x: Option[Product] => Right(x)
      case _ => Left(0)
    }
  }

}
