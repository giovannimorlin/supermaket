package supermarket

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http

import supermarket.service.ProductService

object Main extends App {

  implicit val system = ActorSystem(Behaviors.empty, "supermarket")
  implicit val ec     = system.executionContext

  Http().newServerAt("localhost",8081).bind(ProductService.ServerRoute)
}