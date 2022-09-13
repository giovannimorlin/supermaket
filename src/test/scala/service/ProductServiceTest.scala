package supermarket.service

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.{ScalatestRouteTest, ScalatestUtils}
import akka.http.scaladsl.server._
import Directives._
//import io.circe._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spray.json._

import supermarket.model._

class ProductServiceTest extends AnyWordSpec       //scalatest
                         with Matchers             //scalatest
                         with ScalatestRouteTest   //akka.http.dsl
                         with ScalatestUtils       //akka.http.dsl
                         with ProductJsonProtocol {//supermarket.model

  //add a mock/stub and verify the falilure and the success of a call
  // use testcontainer per creare degli state case ad hoc
  // tabella vuota => verificare cosa ritorna da db
  // innescare un'errore da db (query di un param non valido) =>
  // da doobie e poi gestire l'eccezione
  val testProductService: Route = ProductService.ServerRoute

  "The service should handle the request properly" should {

    "returning all the items in the table products" in {
      // tests:
      Get("/products") ~> testProductService ~> check {
        responseAs[List[Product]].length should be > 1
      }
    }

    "returning the item required" in {
      // tests:
      Get("/products?id=1") ~> testProductService ~> check {
        responseAs[Product] shouldEqual
          (Product(ProductId(1), ProductName("Nutella"), ProductBrand("Ferrero"),
            ProductBrandProducer("Ferrero")))
      }
    }
  }

}