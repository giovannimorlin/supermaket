package supermarket.model
import doobie.{Read, Write}

trait ProductToDBProtocol {
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

