package model

import doobie.{Read, Write}
import spray.json.DefaultJsonProtocol


case class ProductId(id:Int)
case class ProductName(name:String)
case class ProductBrand(brand:String)
case class ProductBrandProducer(brandproducer:String)

case class Product(id:              ProductId,
                   name:            ProductName,
                   brand:           ProductBrand,
                   brandproducer:   ProductBrandProducer,
                   //expirationDate:DateTime,
                   //aislePosition: Int,
                   //rackAisle:     Int,
                   //shelfRack:     Int,
                   //category:      String,
                   //nutritionTable:Option[String]
)

trait ProductJsonProtocol extends DefaultJsonProtocol {
  implicit val productIdFormat = jsonFormat1(ProductId)
  implicit val productNameFormat = jsonFormat1(ProductName)
  implicit val productBrandFormat = jsonFormat1(ProductBrand)
  implicit val productBrandProducerFormat = jsonFormat1(ProductBrandProducer)

  //difficoltà:
  //non riesce a risolere la case class quando c'è il companion obj
  //ho risolto inglobando gli impliciti def nel companion obj
  implicit val productFormat = jsonFormat4(Product)

  //necessari per doobie
  implicit val productRead: Read[Product] =
    Read[(Int, String, String, String)].map {
      case (id, name, brand, brandproducer) =>
        Product(ProductId(id),
          ProductName(name),
          ProductBrand(brand),
          ProductBrandProducer(brandproducer))
    }

  implicit val productWrite: Write[Product] =
    Write[(Int, String, String, String)].contramap {
      case Product(ProductId(id),
      ProductName(name),
      ProductBrand(brand),
      ProductBrandProducer(brandproducer)) => (id, name, brand, brandproducer)

    }
}


/*
case class BrandProducer(id: String,
                        // date: DateTime,
                         producer: String)

case class AisleWharehouse()

case class AisleMarket(id: String,
                           // dateOfLastChange: DateTime,
                            rackAisleMarket: List[RackAisleMarket])

case class RackAisleMarket(id: String,
                          //      date: DateTime,
                                shelfRackAisleMarket: List[ShelfRackAisleMarket])

case class ShelfRackAisleMarket(id: String,
                              //       date: DateTime,
                                     positionInRack: Int,
                                     product:List[Product])// formata da aisle+rack

case class MarketName(id: String,
                  //         date: DateTime,
                           name: String,
                           address: String,
                           aisleMarket: List[AisleMarket],
                           productCategory: List[String],
                           categoryToAisle: Map[AisleMarket,List[String]])

case class ProductPosition(category: String,
                           MarketId: String)
 */



