package supermarket.service

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import supermarket.model.{Product, ProductBrand, ProductBrandProducer, ProductId, ProductName}

//SprayJsonSupport  needed for unmarshalling in test
trait ProductToWebProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  //vengono usati per creare productFormat
  implicit val productIdFormat = jsonFormat1(ProductId)
  implicit val productNameFormat = jsonFormat1(ProductName)
  implicit val productBrandFormat = jsonFormat1(ProductBrand)
  implicit val productBrandProducerFormat = jsonFormat1(ProductBrandProducer)

  //difficoltà:
  //non riesce a risolere la case class quando c'è il companion obj
  //ho risolto inglobando gli impliciti def nel companion obj
  implicit val productFormat = jsonFormat4(Product)

}
