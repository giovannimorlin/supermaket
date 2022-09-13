package supermarket.model

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



