package supermarket.service

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import doobie.implicits._
import doobie.util.transactor.Transactor
import spray.json.enrichAny
import supermarket.core.ProductRepository
import supermarket.model._


object ProductService extends ProductToDBProtocol
                         with ProductToWebProtocol {

  def openPrintIO[A](opIO: IO[A]): IO[Unit] =
    opIO.map { x =>
      print(x)
    }

  val ServerRoute: Route =
    path("products") {
      //localhost:8081/products?id=1
      parameter("id".as[Int]) { id =>
        get {
            val r = ProductRepository.getProductByID(id) match {
            case Right(x) => x match {
              case y:Option[Product] => HttpEntity(ContentTypes.`application/json`, y.toJson.prettyPrint)
            }
            case Left(_) => HttpEntity("Id non trovato")
          }

          //openPrintIO(IOqueryDB).unsafeRunSync()
          complete(r)
        }
      } ~
        //localhost:8081/products
        get {
          val IOqueryDB =
            sql"select * from products"
              .query[Product]
              .to[List]
              .transact(xa)

          val r = ProductRepository.getAllProducts() match {
            case Right(x) => x match {
              case y:List[Product] => HttpEntity(ContentTypes.`application/json`, y.toJson.prettyPrint)
              }
            case Left(x) => HttpEntity("Empty table products")
          }

          //openPrintIO(IOqueryDB).unsafeRunSync()
          complete(r)
        } ~
        post {
          entity(as[Product]) { (p:Product) =>

            //try to write to db
            complete("OK")
          }
        }
    }
}

