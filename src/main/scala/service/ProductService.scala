package supermarket.service

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import cats.effect.IO
import cats.effect.unsafe.implicits.global

import doobie.implicits._
import doobie.util.transactor.Transactor

import spray.json.enrichAny

import supermarket.model._





object ProductService extends ProductJsonProtocol {

  val xa: Transactor[IO] = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql:myproducts",
    "docker",
    "docker"
  )

  def openPrintIO[A](opIO: IO[A]): IO[Unit] =
    opIO.map { x =>
      print(x)
    }


  val ServerRoute: Route =
    path("products") {
      //localhost:8081/products?id=1
      parameter("id".as[Int]) { id =>
        get {
          val IOqueryDB =
            sql"select * from products where id=$id"
              .query[Product]
              .option
              .transact(xa)

          val r = IOqueryDB.unsafeRunSync() match {
            case x: Option[Product] =>
              HttpEntity(ContentTypes.`application/json`, x.toJson.prettyPrint)
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

          val r = IOqueryDB.unsafeRunSync() match {
            case x: List[Product] =>
              HttpEntity(ContentTypes.`application/json`, x.toJson.prettyPrint)
            //ritorna un list of json!!
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

